/**
 * 
 */
package com.odobo.web.rectangle;

import java.util.Collection;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.odobo.domain.Rectangle;
import com.odobo.services.IRectanglesService;

/**
 * Panel displaying some rectangles plus a title in a canvas.
 * 
 * @author reiern70
 *
 */
public abstract class RectanglesPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private WebMarkupContainer canvas;
	
	private static final ResourceReference JS = new JavaScriptResourceReference(RectanglesPanel.class, "Rectangles.js");
	
	@SpringBean
	private IRectanglesService rectanglesService;

	/**
	 * @param id
	 * @param model
	 */
	public RectanglesPanel(String id, IModel<String> title) {
		super(id, title);
		
		Label label = new Label("title", title);
		add(label);		
		canvas = new WebMarkupContainer("canvas");
		add(canvas);
		canvas.setOutputMarkupId(true);
		
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forReference(JS));
		response.render(OnDomReadyHeaderItem.forScript("Odobo.createRectagles('"+canvas.getMarkupId()+"',"+rectanglesService.getRectanglesAsJSON(getRectangles())+");"));
	}

	protected abstract Collection<Rectangle> getRectangles();

	public IRectanglesService getRectanglesService() {
		return rectanglesService;
	}

	public void setRectanglesService(IRectanglesService rectanglesService) {
		this.rectanglesService = rectanglesService;
	}
}
