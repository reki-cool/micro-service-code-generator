<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackage}.${table.className}Mapper">

    <!-- 根据ID查询${table.className}对象对应的数据库记录 -->
    <select id="get${table.className}ById" resultType="${entityPackage}.${table.className}">
        select
        <#list table.cloumns as cloumn>
            <#if cloumn_has_next>
                ${cloumn.cloumnName} as ${cloumn.fieldName},
            <#else>
                ${cloumn.cloumnName} as ${cloumn.fieldName}
            </#if>
        </#list>
        from ${table.tableName}
        <trim prefix="where" prefixOverrides="and | or">
            <if test="id != null">
                and id=${r"#{id}"}
            </if>
        </trim>
    </select>

    <!-- 根据多条件构成的Map集合查询${table.className}列表 -->
    <select id="get${table.className}ListByMap" resultType="${entityPackage}.${table.className}" parameterType="java.util.Map">
        select
        <#list table.cloumns as cloumn>
            <#if cloumn_has_next>
                ${cloumn.cloumnName} as ${cloumn.fieldName},
            <#else>
                ${cloumn.cloumnName} as ${cloumn.fieldName}
            </#if>
        </#list>
        from ${table.tableName}
        <trim prefix="where" prefixOverrides="and | or">
            <#list table.cloumns as cloumn>
                <if test="${cloumn.fieldName} != null and ${cloumn.fieldName}!=''">
                    and ${cloumn.cloumnName}=${r"#{"}${cloumn.fieldName}}
                </if>
            </#list>
        </trim>
        <if test="beginPos != null and pageSize != null ">
            limit ${r"${"}beginPos},${r"${"}pageSize}
        </if>
    </select>

    <!-- 根据多条件构成的Map集合查询${table.className}对象对应的数据库记录总数 -->
    <select id="get${table.className}CountByMap" resultType="Integer"  parameterType="java.util.Map">
        select count(*) from ${table.tableName}
        <trim prefix="where" prefixOverrides="and | or">
        <#list table.cloumns as cloumn>
            <#if cloumn.cloumnType=='VARCHAR' || cloumn.cloumnType=='TEXT'>
                <if test="${cloumn.fieldName} != null and ${cloumn.fieldName}!=''">
                    and ${cloumn.cloumnName}=${r"#{"}${cloumn.fieldName}}
                </if>
            <#else>
                <if test="${cloumn.fieldName} != null and ${cloumn.fieldName}!=''">
                    and ${cloumn.cloumnName}=${r"#{"}${cloumn.fieldName}}
                </if>
            </#if>
        </#list>
        </trim>
    </select>

    <!-- 插入${table.className}对象对应的数据库记录 -->
    <insert id="insert${table.className}" parameterType="${entityPackage}.${table.className}">
        insert into ${table.tableName}(
        <#list table.cloumns as cloumn>
                <#if cloumn_has_next>
                    <#if  cloumn.cloumnName!='id'>
                        ${cloumn.cloumnName},
                    </#if>
                <#else>
                    <#if  cloumn.cloumnName!='id'>
                        ${cloumn.cloumnName})
                    </#if>
                </#if>
        </#list>
        values(
        <#list table.cloumns as cloumn>
            <#if cloumn_has_next>
                <#if  cloumn.cloumnName!='id'>
                     ${r"#{"}${cloumn.fieldName}},
                </#if>
            <#else>
                <#if  cloumn.cloumnName!='id'>
                    ${r"#{"}${cloumn.fieldName}})
                </#if>
            </#if>
        </#list>
    </insert>

    <!-- 更新${table.className}对象对应的数据库记录 -->
    <update id="update${table.className}" parameterType="${entityPackage}.${table.className}">
        update ${table.tableName}
        <trim prefix="set" suffixOverrides="," suffix="where id=${r"#{"}id}">
        <#list table.cloumns as cloumn>
                <#if cloumn_has_next>
                    <#if  cloumn.cloumnName!='id'>
                        <if test="${cloumn.fieldName} != null and ${cloumn.fieldName}!=''">
                            ${cloumn.cloumnName}=${r"#{"}${cloumn.fieldName}},
                        </if>
                    </#if>
                <#else>
                    <#if  cloumn.cloumnName!='id'>
                        <if test="${cloumn.fieldName} != null and ${cloumn.fieldName}!=''">
                            ${cloumn.cloumnName}=${r"#{"}${cloumn.fieldName}}
                        </if>
                    </#if>
                </#if>
            </#list>
        </trim>
    </update>
</mapper>
