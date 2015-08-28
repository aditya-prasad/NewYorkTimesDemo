package com.adityap.nyt.app.ui.flow.main.stories;

import java.util.ArrayList;
import java.util.List;

public class Section
{
    private static List<Section> sections;

    public static List<Section> getSections()
    {
        if(sections == null)
        {
            sections = new ArrayList<>(9);
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.WORLD, "World"));
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.BUSINESS, "Business"));
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.TECHNOLOGY, "Technology"));
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.SCIENCE, "Science"));
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.HEALTH, "Health"));
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.SPORTS, "Sports"));
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.ARTS, "Arts"));
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.FASHION, "Fashion"));
            sections.add(new Section(com.adityap.nyt.domain.model.story.Section.TRAVEL, "Travel"));
        }

        return sections;
    }

    private int code;
    private String name;

    private Section(int code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public int getCode()
    {
        return code;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
