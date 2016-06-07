package by.lskrashchuk.training.parking.webapp.page.file;

import java.io.File;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Bytes;

import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class FileUploadPage extends AbstractPage {

	private FileUploadField fileUpload;
	private String UPLOAD_FOLDER = "C:\\";

	public FileUploadPage() {

		add(new FeedbackPanel("feedback"));

		Form<?> form = new Form<Void>("form") {
		 @Override
		 protected void onSubmit() {

			final FileUpload uploadedFile = fileUpload.getFileUpload();
			if (uploadedFile != null) {

				// write to a new file
				File newFile = new File(UPLOAD_FOLDER
					+ uploadedFile.getClientFileName());

				if (newFile.exists()) {
					newFile.delete();
				}

				try {
					newFile.createNewFile();
					uploadedFile.writeTo(newFile);

					info("saved file: " + uploadedFile.getClientFileName());
				} catch (Exception e) {
					throw new IllegalStateException("Error");
				}
			 }

			}

		};

		// Enable multipart mode (need for uploads file)
		form.setMultiPart(true);

		// max upload size, 100k
		form.setMaxSize(Bytes.kilobytes(100));

		form.add(fileUpload = new FileUploadField("fileUpload"));

		add(form);

	}
}