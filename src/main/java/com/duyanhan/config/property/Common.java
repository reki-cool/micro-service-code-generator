package com.duyanhan.config.property;

import com.duyanhan.config.property.common.Client;
import com.duyanhan.config.property.common.Dao;
import com.duyanhan.config.property.common.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Common {

    private Module module;
    private Dao dao;
    private Client client;
}
