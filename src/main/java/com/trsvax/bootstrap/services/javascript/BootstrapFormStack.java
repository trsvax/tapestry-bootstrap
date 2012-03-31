package com.trsvax.bootstrap.services.javascript;

import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Mapper;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.got5.tapestry5.jquery.utils.JQueryUtils;


public class BootstrapFormStack implements JavaScriptStack{
	
	public static final String STACK_ID = "FormSupportStack";

    private final List<Asset> javaScriptStack;

    private final List<StylesheetLink> stylesheetStack;

    public BootstrapFormStack(final AssetSource assetSource,

                            @Symbol(SymbolConstants.PRODUCTION_MODE)
                            final boolean productionMode)
    {

        final Mapper<String, Asset> pathToAsset = new Mapper<String, Asset>()
        {
            public Asset map(String path)
            {
                return assetSource.getExpandedAsset(path);
            }
        };

        if (productionMode) {

            javaScriptStack = F.flow("classpath:com/trsvax/bootstrap/assets/validation.js")
                               .map(pathToAsset).toList();
        } else {

            javaScriptStack = F.flow("classpath:com/trsvax/bootstrap/assets/validation.js")
                               .map(pathToAsset).toList();
        }

        final Mapper<String, StylesheetLink> pathToStylesheetLink = F.combine(pathToAsset, JQueryUtils.assetToStylesheetLink);

        stylesheetStack = F.flow("${tapestry.jquery.path}/form.css").map(pathToStylesheetLink).toList();

    }

    public List<String> getStacks() {

        return Collections.emptyList();
    }

    public List<Asset> getJavaScriptLibraries() {

        return javaScriptStack;
    }

    public List<StylesheetLink> getStylesheets() {

        return stylesheetStack;
    }

    public String getInitialization() {

        return null;
    }

}
