package fr.openwide.core.wicket.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TArchiveDetector;
import de.schlichtherle.truezip.file.TConfig;
import de.schlichtherle.truezip.util.SuffixSet;
import fr.openwide.core.commons.util.mime.MediaType;
import fr.openwide.core.commons.util.registry.TFileRegistry;

public class TFileZipFileFilter implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TFileZipFileFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		configureGlobally(TConfig.get());
	}
	
	protected void configureGlobally(TConfig trueZipConfig) {
		trueZipConfig.setArchiveDetector(new TArchiveDetector(new SuffixSet(MediaType.APPLICATION_ZIP.supportedExtensions()).toString()));
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			TConfig.push();
			TFileRegistry.open();
			
			chain.doFilter(request, response);
		} finally {
			try {
				TFileRegistry.close();
			} catch (Exception e) {
				LOGGER.error("Error while trying to sync truezip filesystem (TFileRegistry.sync())", e);
			}
			try {
				TConfig.pop();
			} catch (Exception e) {
				LOGGER.error("Error while trying to pop truezip config (TConfig.pop())", e);
			}
		}
	}

	@Override
	public void destroy() { }
}

