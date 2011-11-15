<%@ page import="uk.co.devooght.stock.Sku" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sku.label', default: 'Sku')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${skuInstance}">
        <div class="errors">
            <g:renderErrors bean="${skuInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="stockCode"><g:message code="sku.stockCode.label" default="Stock Code"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: skuInstance, field: 'stockCode', 'errors')}">
                        <g:textField name="stockCode" value="${skuInstance?.stockCode}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="price"><g:message code="sku.price.label" default="Price"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: skuInstance, field: 'price', 'errors')}">
                        <g:textField name="price" value="${fieldValue(bean: skuInstance, field: 'price')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="product"><g:message code="sku.product.label" default="Product"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: skuInstance, field: 'product', 'errors')}">
                        <g:select name="product.id" from="${uk.co.devooght.stock.Product.list()}" optionKey="id"
                                  value="${skuInstance?.product?.id}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
