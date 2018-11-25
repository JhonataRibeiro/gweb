package br.com.crud.gweb.utils;

/**
 * @author jribeiro
 * @date 25/11/18
 */

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public abstract class DTOUtils {

    public static Object convertClassAToClassBDTO(Class<?> aType, Class<?> bType, Object source) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(aType, bType)
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade().map(source, bType);
    }

}



