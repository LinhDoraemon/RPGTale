package com.wingx.pubmine.util;


public class TimeParser
{
  public TimeParser() {}
  

  public static String secondToMinutes(int time)
  {
    int minutes = time / 60;
    int seconds = time % 60;
    String disMinu = (minutes < 10 ? "0" : "") + minutes;
    String disSec = (seconds < 10 ? "0" : "") + seconds;
    String formattedTime = disMinu + ":" + disSec;
    return formattedTime;
  }
}
