import Goods.Good;
import org.junit.Assert;
import org.junit.Test;


public class TestGood {
    @Test
    public void testGetId() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        int result = g.getId();
        Assert.assertEquals(7,result);
    }

    @Test
    public void testGetName() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        String result = g.getName();
        Assert.assertEquals("TestGood",result);
    }

    @Test
    public void testGetImgPath() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        String result = g.getPathImg();
        Assert.assertEquals("TestGood.png",result);
    }

    @Test
    public void testGetCost(){
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        int result = g.getCost();
        Assert.assertEquals(500,result);
    }

    @Test
    public void testGetQuality() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        int result = g.getQuality();
        Assert.assertEquals(200,result);
    }

    @Test
    public void testGetValue() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        g.setVal(2534);
        Assert.assertEquals(2534,g.getVal());
    }

    @Test
    public void testGetPath() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        Assert.assertEquals("File/Images/ImagesGoods/TestGood.png",g.getPath());
    }

    @Test
    public void testSetValue() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        g.setVal(93);
        Assert.assertEquals(93,g.getVal());
    }

    @Test
    public void testSetId(){
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        g.setId(8);
        Assert.assertEquals(8,g.getId());
    }

    @Test
    public void testSetQuality(){
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        g.setQuality(800);
        Assert.assertEquals(800,g.getQuality());
    }

    @Test
    public void testSetCost(){
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        g.setCost(123);
        Assert.assertEquals(123,g.getCost());
    }

    @Test
    public void testSetName() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        g.setName("Cucumber");
        Assert.assertEquals("Cucumber",g.getName());
    }

    @Test
    public void testSetImgPath() {
        Good g = new Good("TestGood.png","TestGood",500,200,7);
        g.setPathImg("Cucumber.png");
        Assert.assertEquals("File/Images/ImagesGoods/Cucumber.png",g.getPathImg());
    }
}
