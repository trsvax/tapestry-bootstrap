package com.trsvax.bootstrap.services;

import java.io.IOException;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;

public class CKEditorRequestFilter implements RequestFilter {
	

	final private static String[] FOLDERS = { "/lang/", "/skins/","/plugins/"};

	final private AssetSource assetSource;
	final private Logger logger;


	public CKEditorRequestFilter(AssetSource assetSource, Logger logger) {
		super();
		this.assetSource = assetSource;
		this.logger = logger;
	}

	public boolean service(Request request, Response response, RequestHandler handler) throws IOException {

		String path = request.getPath();
		boolean handled = false;

		// we only handle requests for ckeditor asset URLs and which weren't redirected already
		if (path.contains("ckeditor") && request.getParameter("redirected") == null) {
			
			if ( path.endsWith("ckeditor/config.js")) {
				Asset asset = assetSource.getClasspathAsset("/META-INF/assets/ckeditor/config.js");

				// we create the redirection target URL with "/?redirected" in the end
				// so we can spot and avoid redirection infinite loops.
				final String redirectionUrl = asset.toClientURL() + "/?redirected";
				response.sendRedirect(redirectionUrl);
				return true;
			}
			
			if ( path.endsWith("ckeditor/styles.js")) {
				Asset asset = assetSource.getClasspathAsset("/META-INF/assets/ckeditor/styles.js");

				// we create the redirection target URL with "/?redirected" in the end
				// so we can spot and avoid redirection infinite loops.
				final String redirectionUrl = asset.toClientURL() + "/?t=D2LI&redirected";
				response.sendRedirect(redirectionUrl);
				return true;
			}

			logger.info("path {}",path);
			for (String folder : FOLDERS) {

				if (path.contains(folder)) {

					// for some reason, the requests for wymiframe.css came with this wrong URL,
					// so we change it to the correct one.
					if (path.contains("/iframe/default/wymiframe.html/wymiframe.css")) {
						path = path.replace(
								"/iframe/default/wymiframe.html/wymiframe.css", 
								"/iframe/default/wymiframe.css");
					}

					// extract the WYMeditor asset being requested.
					path = path.substring(
							path.indexOf('/', path.indexOf("/ckeditor/") + "/ckeditor/".length() + 1));

					// find its location in the classpath.
					String location = "/META-INF/assets/ckeditor" + folder +  path;

					logger.info("location {}",location);
					// create an Asset pointing to it. Its URL will contain the right checksum
					// for this file
					Asset asset = assetSource.getClasspathAsset(location);

					// we create the redirection target URL with "/?redirected" in the end
					// so we can spot and avoid redirection infinite loops.
					final String redirectionUrl = asset.toClientURL() + "/?redirected";

					// finally, we redirect.
					response.sendRedirect(redirectionUrl);
					handled = true;
					break;

				}

			}

		}

		// if we didn't redirect, we pass this request to the next RequestFilter in the pipeline
		return handled ? handled : handler.service(request, response);

	}

}
