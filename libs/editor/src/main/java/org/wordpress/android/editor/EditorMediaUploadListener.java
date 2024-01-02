package org.wordpress.android.editor;

import org.wordpress.android.util.helpers.MediaFile;

public interface EditorMediaUploadListener {
    void onMediaUploadRetry(String localId, EditorFragmentAbstract.MediaType mediaType);
    void onMediaUploadReattached(String localId, float currentProgress);
    void onMediaUploadSucceeded(String localId, MediaFile mediaFile);
    void onMediaUploadProgress(String localId, float progress);
    void onMediaUploadFailed(String localId, float progress);
    void onMediaUploadPaused(String localId, float progress);
    void onGalleryMediaUploadSucceeded(long galleryId, long remoteId, int remaining);
}
