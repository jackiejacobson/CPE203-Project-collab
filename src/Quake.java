import processing.core.PImage;

import java.util.List;


public class Quake implements AnimatedEntity {


    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int actionPeriod;
    private int animationPeriod;

    public Quake(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
    }
    public Point getPosition(){
        return position;
    }
    public Point setPosition(Point point){
        this.position = new Point(point.x, point.y);
        return position;
    }
    public List<PImage> getImages(){
        return images;
    }
    public int getImageIndex(){
        return imageIndex;
    }


    public void scheduleActions(
           // Entity entity,
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
                scheduler.scheduleEvent(this,
                        ActionFactory.createActivityAction(this, world, imageStore),
                        this.actionPeriod);
        int QUAKE_ANIMATION_REPEAT_COUNT = 10;
        scheduler.scheduleEvent(this, ActionFactory.createAnimationAction(this,
                QUAKE_ANIMATION_REPEAT_COUNT),
                        this.getAnimationPeriod());
    }

    public void executeActivity(
            //Entity entity,
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }



    public int getAnimationPeriod() {
                return animationPeriod;

    }

    public PImage getCurrentImage( ) {
        return getImages().get(getImageIndex());
    }

    public void nextImage() {
        imageIndex = (imageIndex + 1) % images.size();
    }
}