package Goods;

public class Good {
    String path = "File/Images/ImagesGoods/";
    String startPath = "File/Images/ImagesGoods/";
    String pathImg ;
    String name ;
    private int cost ;
    private int quality ;
    private int val ;
    private int id ;

    public Good(String imgPath, String name, int cost, int quality, int id){
        val = 0;
        path = path+imgPath;
        this.pathImg = imgPath;
        this.name = name;
        this.cost = cost;
        this.quality = quality;
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPathImg(String path){
        this.pathImg = startPath+path;
    }

    public String getPathImg(){
        return this.pathImg;
    }

    public String getName(){
        return name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setQuality(int quality){
        this.quality = quality;
    }

    public String getPath() {
        return path;
    }

    public int getCost() {
        return cost;
    }

    public int getQuality() {
        return quality;
    }

    public int getId() {
        return id;
    }
}
