package by.lskrashchuk.training.parking.webapp.page.color;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import by.lskrashchuk.training.parking.datamodel.Color;
import by.lskrashchuk.training.parking.service.ColorService;

public class ColorEditPanel extends Panel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private ColorService colorService;

    private Color color;

    private ModalWindow modalWindow;

    public ColorEditPanel(ModalWindow modalWindow, Color color) {
        super(modalWindow.getContentId());
        this.color = color;
        this.modalWindow = modalWindow;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form<Color> form = new Form<>("form", new CompoundPropertyModel<>(color));
        add(form);
        form.setOutputMarkupId(true);
//        form.add(new TextField<>("name"));


		TextField<String> colorField = new TextField<>("name");
		colorField.setLabel(new ResourceModel("car.color"));
		colorField.setRequired(true);
		colorField.setOutputMarkupId(true);
		form.add(colorField);

		FeedbackPanel feedback = new FeedbackPanel("feedback");
		form.add(feedback);
		feedback.setOutputMarkupId(true);

        
        form.add(new AjaxSubmitLink("saveColor") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
				target.add(feedback);

                if (colorService.getByName(color.getName()) != null) {
					String localizedMessage = getString("car.edit.newcolor.exist");
					this.error(localizedMessage);
					target.add(feedback);
                }
                else {
                colorService.saveOrUpdate(color);
                modalWindow.close(target);
                }
            }
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				super.onError(target, form);
				target.add(feedback);
			}
        });

        form.add(new AjaxLink("cancel") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.close(target);
            }
        });
        

    }

}
