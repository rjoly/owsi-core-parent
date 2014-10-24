package fr.openwide.core.jpa.more.business.task.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embeddable;

import org.bindgen.Bindable;

import com.google.common.base.Throwables;

import fr.openwide.core.commons.util.report.BatchReportItem;
import fr.openwide.core.jpa.more.business.task.util.TaskResult;

@Embeddable
@Bindable
public class TaskExecutionResult implements Serializable {
	
	private static final long serialVersionUID = 3669496606035758060L;
	
	/**
	 * Indique que la tâche s'est déroulée jusqu'au bout, sans rapport d'exécution.
	 */
	public static final TaskExecutionResult completed() {
		return completed((BatchReportBean) null);
	}
	
	/**
	 * Indique que la tâche s'est déroulée jusqu'au bout.
	 * @param report peut contenir des alertes ou erreurs
	 */
	public static final TaskExecutionResult completed(BatchReportBean report) {
		return new TaskExecutionResult(report, null);
	}
	
	/**
	 * Indique que la tâche a rencontrée une erreur et ne s'est pas déroulée jusqu'au bout.
	 * La transaction subira un rollback.
	 * @param throwable l'exception, dont la stacktrace est stockée
	 */
	public static final TaskExecutionResult failed(Throwable throwable) {
		return failed(null, throwable);
	}
	
	/**
	 * Indique que la tâche a rencontrée une erreur et ne s'est pas déroulée jusqu'au bout.
	 * Le rapport peut donner des informations sur l'execution de la tâche avant l'erreur rencontrée.
	 * La transaction subira un rollback.
	 * @param report peut contenir des alertes ou erreurs
	 * @param throwable l'exception, dont la stacktrace est stockée
	 */
	public static final TaskExecutionResult failed(BatchReportBean report, Throwable throwable) {
		TaskExecutionResult executionResult = new TaskExecutionResult(report, throwable);
		executionResult.setResult(TaskResult.FATAL);
		return executionResult;
	}
	
	/**
	 * Pas de result tant que la tâche n'a pas été exécutée.
	 * Calculé automatiquement en fonction du rapport, faire attention si on le définit à la main.
	 */
	private TaskResult result;
	
	private BatchReportBean report;
	
	private Throwable throwable;
	
	protected TaskExecutionResult() { }
	
	private TaskExecutionResult(BatchReportBean report, Throwable throwable) {
		this.report = report;
		this.throwable = throwable;
		computeResult();
	}
	
	private void computeResult() {
		setResult(TaskResult.SUCCESS);
		if (report != null) {
			for (List<BatchReportItem> contextItems : report.getItems().values()) {
				for (BatchReportItem item : contextItems) {
					switch (item.getSeverity()) {
					case TRACE:
					case DEBUG:
					case INFO:
						break;
					case WARN:
						setResult(TaskResult.WARN);
						break;
					case ERROR:
						setResult(TaskResult.ERROR);
						return;
					}
				}
			}
		}
	}

	public TaskResult getResult() {
		return result;
	}

	public void setResult(TaskResult result) {
		if (this.result == null) {
			this.result = result;
		} else if (result != null && result.ordinal() > this.result.ordinal()) {
			this.result = result;
		}
	}

	public BatchReportBean getReport() {
		return report;
	}

	public void setReport(BatchReportBean report) {
		this.report = report;
		computeResult();
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public String getStackTrace() {
		if (throwable == null) {
			return null;
		}
		return Throwables.getStackTraceAsString(throwable);
	}

}
