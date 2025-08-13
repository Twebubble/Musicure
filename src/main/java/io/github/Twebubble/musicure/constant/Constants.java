package io.github.Twebubble.musicure.constant;

public class Constants {
    /* 歌曲图片，歌手图片，歌曲文件，歌单图片等文件的存放路径 */
    public static String ASSETS_PATH = System.getProperty("user.dir");
//    打印当前目录
//    public static void main(String[] args) {
//        System.out.println("当前工作目录: " + ASSETS_PATH);
//    }

    public static String AVATOR_IMAGES_PATH = "file:" + ASSETS_PATH + "/img/avatorImages/";
    public static String SONGLIST_PIC_PATH = "file:" + ASSETS_PATH + "/img/songListPic/";
    public static String SONG_PIC_PATH = "file:" + ASSETS_PATH + "/img/songPic/";
    public static String SONG_PATH = "file:" + ASSETS_PATH + "/song/";
    public static String SINGER_PIC_PATH = "file:" + ASSETS_PATH + "/img/singerPic/";
    public static String BANNER_PIC_PATH = "file:" + ASSETS_PATH + "/img/swiper/";

    /* 盐值加密：密码加密使用的盐值 */
    public static String SALT = "zyt";
}