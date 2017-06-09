package index;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettingsService;

public class CoreFrenchMinimalStemFilterFactory extends AbstractTokenFilterFactory {

	@Inject
	public CoreFrenchMinimalStemFilterFactory(Index index, IndexSettingsService indexSettingsService, Environment env, @Assisted String name, @Assisted Settings settings) {
		super(index, indexSettingsService.getSettings(), name, settings);
	}

	public TokenStream create(TokenStream input) {
		return new CoreFrenchMinimalStemFilter(input);
	}

}
