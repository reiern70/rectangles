package com.odobo.web;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

import com.odobo.domain.Rectangle;
import com.odobo.services.IRectanglesService;

public class RectanglesPage extends WebPage {
	private static final long serialVersionUID = 1L;

	private Integer numberOfRectangles = 5;
	
	@SpringBean
	private IRectanglesService rectanglesService;
	
	private Collection<Rectangle> rectangles = new ArrayList<Rectangle>();
	
	private Collection<Rectangle> hrectangles = new ArrayList<Rectangle>();
	
	public RectanglesPage(final PageParameters parameters) {
		super(parameters);
		
		Form<Void> form = new Form<Void>("form") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				rectangles = rectanglesService.getRandomHorizontalyAdjacentRectangles(numberOfRectangles);
				hrectangles = rectanglesService.getMinimumNumberOfHorizontalRectangles(rectangles);
			}
		};
		
		add(form);
		
		form.add(new TextField<Integer>("numberOfRectangles", new PropertyModel<Integer>(this, "numberOfRectangles"))
				.add(new RangeValidator<Integer>(5, 15))
				.setRequired(true));
				
    }
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		RepeatingView randomRectangles = new RepeatingView("randomRectangles");
		
		int posX = 40;
		int posY = 300;
				
		for(Rectangle rectangle: rectangles) {
			WebMarkupContainer wm = new WebMarkupContainer(randomRectangles.newChildId());	
			StringBuilder builder = new StringBuilder();
			builder.append("position:absolute; border: 1px solid black;");
			builder.append("left:"+(posX+rectangle.getX())+"px;");
			builder.append("top:"+(posY+rectangle.getY())+"px;");
			builder.append("width:"+(rectangle.getWidth())+"px;");
			builder.append("height:"+(rectangle.getHeight())+"px;");
			wm.add(new AttributeModifier("style", Model.of(builder.toString())));
			randomRectangles.add(wm);
			
		}
		addOrReplace(randomRectangles);	
		
		
		RepeatingView hrandomRectangles = new RepeatingView("hrandomRectangles");
		
		posY = 600;
				
		for(Rectangle rectangle: hrectangles) {
			WebMarkupContainer wm = new WebMarkupContainer(randomRectangles.newChildId());	
			StringBuilder builder = new StringBuilder();
			builder.append("position:absolute; border: 1px solid black;");
			builder.append("left:"+(posX+rectangle.getX())+"px;");
			builder.append("top:"+(posY+rectangle.getY())+"px;");
			builder.append("width:"+(rectangle.getWidth())+"px;");
			builder.append("height:"+(rectangle.getHeight())+"px;");
			wm.add(new AttributeModifier("style", Model.of(builder.toString())));
			hrandomRectangles.add(wm);
			
		}
		addOrReplace(hrandomRectangles);	
	}

	public Integer getNumberOfRectangles() {
		return numberOfRectangles;
	}

	public void setNumberOfRectangles(Integer numberOfRectangles) {
		this.numberOfRectangles = numberOfRectangles;
	}

	public IRectanglesService getRectanglesService() {
		return rectanglesService;
	}

	public void setRectanglesService(IRectanglesService rectanglesService) {
		this.rectanglesService = rectanglesService;
	}
}
