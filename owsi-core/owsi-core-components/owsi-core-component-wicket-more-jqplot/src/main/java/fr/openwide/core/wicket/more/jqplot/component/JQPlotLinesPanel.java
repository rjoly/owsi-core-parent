package fr.openwide.core.wicket.more.jqplot.component;

import java.util.Locale;
import java.util.Map;

import fr.openwide.core.wicket.more.jqplot.config.AbstractJQPlotConfigurer;
import fr.openwide.core.wicket.more.jqplot.data.adapter.IJQPlotDataAdapter;
import fr.openwide.core.wicket.more.jqplot.data.adapter.JQPlotDataAdapters;
import nl.topicus.wqplot.options.PlotOptions;
import nl.topicus.wqplot.options.PlotSeries;
import nl.topicus.wqplot.options.PlotTick;

public class JQPlotLinesPanel<S, K, V extends Number & Comparable<V>> extends JQPlotPanel<S, K, V> {

	private static final long serialVersionUID = -5575918534912813908L;

	public JQPlotLinesPanel(String id, IJQPlotDataAdapter<S, K, V> dataAdapter) {
		super(id, JQPlotDataAdapters.fix(dataAdapter));
		
		add(new AbstractJQPlotConfigurer<S, K>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void configure(PlotOptions options, Map<? extends S, PlotSeries> seriesMap,
					Map<? extends K, PlotTick> keysMap, Locale locale) {
				options.getSeriesDefaults()
						.setRendererOptions(getOptionsFactory().newPlotLineRendererOptions());
			}
		});
	}
}
