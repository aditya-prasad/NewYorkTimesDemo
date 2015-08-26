package com.adityap.nyt.data.story.cloud;

import com.adityap.nyt.domain.model.story.Section;

public abstract class SectionMapper
{
    public static String getString(int section)
    {
        switch (section)
        {
            case Section.WORLD:
                return "world";
            case Section.BUSINESS:
                return "business";
            case Section.TECHNOLOGY:
                return "technology";
            case Section.SCIENCE:
                return "science";
            case Section.HEALTH:
                return "health";
            case Section.SPORTS:
                return "sports";
            case Section.ARTS:
                return "arts";
            case Section.FASHION:
                return "fashion";
            case Section.TRAVEL:
                return "travel";
            default:
                return "world";
        }
    }

    public static int getCode(String name)
    {
        if (name.equalsIgnoreCase("business"))
        {
            return Section.BUSINESS;
        }
        else if (name.equalsIgnoreCase("technology"))
        {
            return Section.TECHNOLOGY;
        }
        else if (name.equalsIgnoreCase("science"))
        {
            return Section.SCIENCE;
        }
        else if (name.equalsIgnoreCase("health"))
        {
            return Section.HEALTH;
        }
        else if (name.equalsIgnoreCase("sports"))
        {
            return Section.SPORTS;
        }
        else if (name.equalsIgnoreCase("arts"))
        {
            return Section.ARTS;
        }
        else if (name.equalsIgnoreCase("fashion"))
        {
            return Section.FASHION;
        }
        else if (name.equalsIgnoreCase("travel"))
        {
            return Section.TRAVEL;
        }
        else
        {
            return Section.WORLD;
        }
    }
}
