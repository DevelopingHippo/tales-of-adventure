public class WorldTime implements Runnable {

    private String day = "Monday";
    private int hour = 0;
    private int minute = 0;
    private int second = 0;


    public WorldTime()
    {
        Thread timeThread = new Thread(this);
        timeThread.start();
    }


    @Override
    public void run()
    {
        worldClock();
    }

    private void worldClock()
    {

        while(true)
        {
            if(this.second >= 60)
            {
                this.second = 0;
                minute++;
            }
            if(this.minute >= 60)
            {
                this.minute = 0;
                this.hour++;
            }
            if(this.hour >= 24)
            {
                this.hour = 0;
                if(this.day.equalsIgnoreCase("Monday"))
                {
                    this.day = "Tuesday";
                }
                else if(this.day.equalsIgnoreCase("Tuesday"))
                {
                    this.day = "Wednesday";
                }
                else if(this.day.equalsIgnoreCase("Wednesday"))
                {
                    this.day = "Thursday";
                }
                else if(this.day.equalsIgnoreCase("Thursday"))
                {
                    this.day = "Friday";
                }
                else if(this.day.equalsIgnoreCase("Friday"))
                {
                    this.day = "Saturday";
                }
                else if(this.day.equalsIgnoreCase("Saturday"))
                {
                    this.day = "Monday";
                }
            }
            this.second++;
            try {
                Thread.sleep(666);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public String getTime()
    {
        return this.hour + ":" + this.minute + ":" + this.second;
    }
    public String getDay()
    {
        return this.day;
    }
}
