import java.util.ArrayList;

public class GoodsMarket {

    ArrayList<Resource> resources;

    public GoodsMarket() {
        createSimpleMarket();
    }

    public void tick() {
        updateResources();
    }

    private void updateResources() {
        for(Resource res : resources) {
            res.tick();
        }
    }

    private void createSimpleMarket() {
        resources = new ArrayList<Resource>();
        Oil oil = new Oil(this, 25, 10);
        resources.add(oil);
    }
}
