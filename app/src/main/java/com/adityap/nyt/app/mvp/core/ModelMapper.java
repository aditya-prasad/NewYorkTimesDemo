package com.adityap.nyt.app.mvp.core;

import java.util.List;

public interface ModelMapper<Entity, Model>
{
    Model map(Entity entity);

    List<Model> map(List<Entity> entities);
}
