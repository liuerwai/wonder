package com.liuxg.wonder.po;

import com.liuxg.wonder.constant.Properties;
import com.liuxg.wonder.constant.UploadType;
import com.liuxg.wonder.util.FileUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    public static final String uploadImage = "images/no_album.jpg";

    //编号
    private String id;
    //姓名
    private String name;
    // 生日
    private String birthday;
    //性别
    private String sex;
    //身高
    private String height;
    //胸围
    private String chest;
    //腰围
    private String waist;
    //臀围
    private String hips;
    //鞋码
    private String shoeSize;
    //眼睛眼色
    private String eyeColor;
    //发色
    private String hairColor;
    //肤色
    private String skinColor;
    // 作品图片封面地址
    private String opusTitle;
    // 作品图片地址
    private Map<String, String> opus;
    // 素颜图片封面地址
    private String makeupTitle;
    // 素颜图片地址
    private Map<String, String> makeup;
    // 视频图片封面地址
    private String videioTite;
    // 视频图片封面
    private Map<String, String> video;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHips() {
        return hips;
    }

    public void setHips(String hips) {
        this.hips = hips;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOpusTitle() {
        return StringUtils.isEmpty(opusTitle) ? uploadImage : opusTitle;
    }

    public void setOpusTitle(String opusTitle) {
        this.opusTitle = opusTitle;
    }

    public Map<String, String> getOpus() {
        return opus == null ? opus = new HashMap<>() : opus;
    }

    public void setOpus(Map<String, String> opus) {
        this.opus = opus;
    }

    public String getMakeupTitle() {
        return StringUtils.isEmpty(makeupTitle) ? uploadImage : makeupTitle;
    }

    public void setMakeupTitle(String makeupTitle) {
        this.makeupTitle = makeupTitle;
    }

    public Map<String, String> getMakeup() {
        return makeup == null ? makeup = new HashMap<>() : makeup;
    }

    public void setMakeup(Map<String, String> makeup) {
        this.makeup = makeup;
    }

    public String getVideioTite() {
        return StringUtils.isEmpty(videioTite) ? uploadImage : videioTite;
    }

    public void setVideioTite(String videioTite) {
        this.videioTite = videioTite;
    }

    public Map<String, String> getVideo() {
        return video == null ? video = new HashMap<>() : video;
    }

    public void setVideo(Map<String, String> video) {
        this.video = video;
    }

    public void addOps(String name, String webPath) {
        if (getOpus() == null) {
            opus = new HashMap<>();
        }
        opus.put(name, webPath);
    }

    public void removeOps(String name) {
        if (getOpus() == null) {
            opus = new HashMap<>();
        }
        opus.remove(name);
    }

    public void addMakeup(String name, String webPath) {
        if (getMakeup() == null) {
            makeup = new HashMap<>();
        }
        makeup.put(name, webPath);
    }

    public void removeMakeup(String name) {
        if (getMakeup() == null) {
            makeup = new HashMap<>();
        }
        makeup.remove(name);
    }

    public void addVideo(String name, String webPath) {
        if (getVideo() == null) {
            video = new HashMap<>();
        }
        video.put(name, webPath);
    }

    public void removeVideo(String name) {
        if (getVideo() == null) {
            video = new HashMap<>();
        }
        video.remove(name);
    }

    public String getImageFileBathPath() {
        return Properties.ImagePath + "model" + File.separator + getName() + File.separator;
    }

    public String getImageWebBathPath() {
        return "images/model/" + getName() + "/";
    }

    public String getOpusFileBathPath() {
        return getImageFileBathPath() + "opus" + File.separator;
    }

    public String getOpusWebBathPath() {
        return getImageWebBathPath() + "opus" + "/";
    }

    public String getMakeupFileBathPath() {
        return getImageFileBathPath() + "makeup" + File.separator;
    }

    public String getMakeupWebBathPath() {
        return getImageWebBathPath() + "makeup" + "/";
    }

    public String getVideoFileBathPath() {
        return getImageFileBathPath() + "video" + File.separator;
    }

    public String getVideoWebBathPath() {
        return getImageWebBathPath() + "video" + "/";
    }


    public void saveWebPath(String type, String file) {
        String name = FileUtils.getFileName(file);
        for (UploadType item : UploadType.values()) {
            if (item.type.equals(type)) {
                if (item == UploadType.OPUS) {
                    addOps(name, getOpusWebBathPath() + name);
                }
                if (item == UploadType.OPUS_Title) {
                    setOpusTitle(getImageWebBathPath() + name);
                }
                if (item == UploadType.MAKEUP) {
                    addMakeup(name, getMakeupWebBathPath() + name);
                }
                if (item == UploadType.MAKEUP_TITLE) {
                    setMakeupTitle(getImageWebBathPath() + name);
                }
                if (item == UploadType.VIDEO) {
                    addVideo(name, getVideoWebBathPath() + name);
                }
                if (item == UploadType.VIDEO_TITLE) {
                    setVideioTite(getImageWebBathPath() + name);
                }
            }
        }
    }


    public void removeWebPath(String type, String file) {
        String fileName = FileUtils.getFileName(file);
        for (UploadType item : UploadType.values()) {
            if (item.type.equals(type)) {
                if (item == UploadType.OPUS) {
                    removeOps(fileName);
                }
                if (item == UploadType.OPUS_Title) {
                    setOpusTitle(null);
                }
                if (item == UploadType.MAKEUP) {
                    removeMakeup(fileName);
                }
                if (item == UploadType.MAKEUP_TITLE) {
                    setMakeupTitle(null);
                }
                if (item == UploadType.VIDEO) {
                    removeVideo(fileName);
                }
                if (item == UploadType.VIDEO_TITLE) {
                    setVideioTite(null);
                }
            }
        }
    }

}
