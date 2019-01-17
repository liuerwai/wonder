package com.liuxg.wonder.constant;

import com.liuxg.wonder.po.Model;

public enum UploadType {

    OPUS("opus"),
    OPUS_Title("opusTitle"),
    MAKEUP("makeup"),
    MAKEUP_TITLE("makeupTitle"),
    VIDEO("video"),
    VIDEO_TITLE("videoTitle"),
    HOME_IMG("homeImage"),
    INDEX_VIDEO("indexVideo");

    public String type;

    UploadType(String type) {
        this.type = type;
    }

    public static String getBathPath(String type, Model model) {
        for (UploadType item : UploadType.values()) {
            if (item.type.equals(type)) {
                if (item == OPUS) {
                    return model.getOpusFileBathPath();
                }
                if (item == OPUS_Title) {
                    return model.getImageFileBathPath();
                }
                if (item == MAKEUP) {
                    return model.getMakeupFileBathPath();
                }
                if (item == MAKEUP_TITLE) {
                    return model.getImageFileBathPath();
                }
                if (item == VIDEO) {
                    return model.getVideoFileBathPath();
                }
                if (item == VIDEO_TITLE) {
                    return model.getImageFileBathPath();
                }
            }
        }
        return "";
    }
}
