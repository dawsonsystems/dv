<%@ page import="uk.co.devooght.stock.Sku" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sku.label', default: 'Sku')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'sku.id.label', default: 'Id')}"/>

                <g:sortableColumn property="stockCode"
                                  title="${message(code: 'sku.stockCode.label', default: 'Stock Code')}"/>

                <g:sortableColumn property="price" title="${message(code: 'sku.price.label', default: 'Price')}"/>

                <th><g:message code="sku.product.label" default="Product"/></th>

            </tr>
            </thead>
            <tbody>
            <g:each in="${skuInstanceList}" status="i" var="skuInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${skuInstance.id}">${fieldValue(bean: skuInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: skuInstance, field: "stockCode")}</td>

                    <td>${fieldValue(bean: skuInstance, field: "price")}</td>

                    <td>${fieldValue(bean: skuInstance, field: "product")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${skuInstanceTotal}"/>
    </div>
</div>
</body>
</html>
