package by.lskrashchuk.training.parking.webapp.common.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource.Attributes;

public class ImageResource {

	// has to save this. or get the image another way!
	private byte[] image;

	public ImageResource(byte[] image) {
		this.image = image;
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		is.close();
		return bytes;
	}

	public static Image createImage(String id, byte[] imageData) {
		NonCachingImage chartImage = new NonCachingImage(id) {
			private static final long serialVersionUID = 1L;

			@Override
			protected DynamicImageResource getImageResource() {
				return new DynamicImageResource() {
					private static final long serialVersionUID = 1L;

					@Override
					protected byte[] getImageData(Attributes attributes) {
						return imageData;
					}
				};
			}

		};
		chartImage.setOutputMarkupId(true);
		chartImage.setOutputMarkupPlaceholderTag(true);
		return chartImage;
	}

}
