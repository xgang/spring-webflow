/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.webflow.mvc.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.webflow.context.servlet.DefaultAjaxHandler;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;
import org.springframework.webflow.execution.View;

/**
 * Tiles view implementation that is able to handle partial rendering for Spring
 * Javascript Ajax requests.
 *
 * <p>This implementation uses the {@link DefaultAjaxHandler}
 * by default to determine whether the current request is an Ajax request. On an
 * Ajax request for an active flow execution, the fragments set by a {@code <render>}
 * action will be respected, otherwise the parent {@link AjaxTiles3View}'s resolution algorithm
 * will be applied.
 *
 * @author Rossen Stoyanchev
 * @since 2.4
 */
public class FlowAjaxTiles3View extends AjaxTiles3View {


	protected String[] getRenderFragments(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {

		RequestContext context = RequestContextHolder.getRequestContext();
		if (context == null) {
			return super.getRenderFragments(model, request, response);
		}

		String[] fragments = (String[]) context.getFlashScope().get(View.RENDER_FRAGMENTS_ATTRIBUTE);
		if (fragments == null) {
			return super.getRenderFragments(model, request, response);
		}

		return fragments;
	}

}
