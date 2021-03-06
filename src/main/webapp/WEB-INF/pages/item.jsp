<div class="col-lg-4 p-2">
    <c:choose>
        <c:when test="${item['class'].simpleName eq 'Startup'}">
            <%-- Will show the startup block --%>
            <a class="btn btn-light btn-block border-success text-success item-hight pb-0"
               href="${pageContext.request.contextPath}/startups/${item.id}" role="button">
                <div class="btn-block item-hight text-dark">
                    <i class="material-icons f24 float-right text-success50">work</i>
                    <div class="text-left mb-1">${item.industry.name}</div>
                    <div class="text-left mb-1">${item.country.name}</div>
                    <hr class="my-2">
                    <div class="description-height">
                        <div class="mb-2">
                            <h4>${item.name}</h4>
                        </div>
                        <div class="description">
                                ${item.description}
                        </div>
                    </div>
                    <hr class="my-2">
                    <div class="float-right text-success">
                        <h5>${item.budget} &#8372;</h5>
                    </div>
                    <div class="text-left mb-2">
                        <c:set var="dateOf" value="${item.registrationDate}"/>
                        <%@include file="patterns/date_pattern.jsp"%>
                    </div>
                </div>
            </a>
        </c:when>
        <c:when test="${item['class'].simpleName eq 'Offer'}">
            <%-- Will show the offer block --%>
            <a class="btn btn-light btn-block border-primary text-primary item-hight pb-0"
               href="${pageContext.request.contextPath}/offers/${item.id}" role="button">
                <div class="btn-block item-hight text-dark">
                    <i class="material-icons f24 float-right text-primary50">attach_money</i>
                    <div class="text-left mb-1">${item.industry.name}</div>
                    <div class="text-left mb-1">${item.country.name}</div>
                    <hr class="my-2">
                    <div class="description-height">
                        <div class="description pt-1">
                                ${item.description}
                        </div>
                    </div>
                    <hr class="my-2">
                    <div class="float-right align-bottom text-primary"><h5>${item.budget} &#8372;</h5></div>
                </div>
            </a>
        </c:when>
    </c:choose>
</div>