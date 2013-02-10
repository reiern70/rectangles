package com.odobo.web.rectangle;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
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
	
	@SuppressWarnings("serial")
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
						
		addOrReplace(new RectanglesPanel("randomRectangles", new ResourceModel("randomHorizontal")) {
			
			@Override
			protected Collection<Rectangle> getRectangles() {
				return rectangles;
			}
		});
		
		addOrReplace(new RectanglesPanel("hrandomRectangles", new ResourceModel("hrandomRectangles")) {
			
			@Override
			protected Collection<Rectangle> getRectangles() {
				return hrectangles;
			}
		});

					
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
