<%@ page import="com.yourcompany.api.Template" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Include header -->
<jsp:include page="includes/header.jsp" flush="true"/>

<!-- Insert title -->
<title>Fog: Bestilling</title>

<body class="d-flex flex-column h-100 bg-light">

<!-- Include navbar -->
<jsp:include page="includes/navs/navbar.jsp" flush="true"/>

<main role="main" class="container flex-shrink-0">
    <jsp:include page="includes/modals/userlogoutmodal.jsp" flush="true"/>

    <h1>Profil side</h1>

    <div class="row">
        <div class="col-md-4"
             style="background-color: rgba(173,216,230, 0.2); border-radius: 30px; padding: 15px; height: 380px">
            <h3>Information</h3>
            <p>Navn: ${sessionScope.user.name}</p>
            <p>Email: ${sessionScope.user.email}</p>
            <button type="button" class="btn btn-secondary">Rediger Information</button>

            <h3 style="padding-top: 40px">Sikkerhed</h3>
            <button type="button" class="btn btn-secondary">Rediger password</button>
        </div>
        <div class="col-md-8">

            <!-- Forespørgelser START -->
            <h3 style="padding-top: 20px">Forespørgelser</h3>
            <c:if test="${requestScope.preorder == null}">
                <p>Du har ikke nogle forespørgelser</p>
            </c:if>

            <!-- Untaken preorders START -->
            <c:if test="${requestScope.untakenpreorders != null}">
                <h3 style="padding-top: 20px">Venter på salgsmedarbejder</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Evt. besked</th>
                            <th scope="col">Vare</th>
                        </tr>
                        </thead>
                        <tbody>

                        <!-- doublecount = used to jump to the next letter in alphabet
                             count  = used to know where in every list we are
                         -->
                        <c:set var="doublecountuntaken" value="0" scope="page"/>
                        <c:set var="alphabetuntakenpreorder"
                               value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z"/>
                        <c:set var="untakenpreordercount" value="0" scope="page"/>
                        <c:forEach var="untakenpreorder" items="${requestScope.untakenpreorders}">
                            <form action="Main" method="POST">
                                <input type="hidden" name="target" value="showpreordercarport">
                                <input type="hidden" name="preorderid"
                                       value="${untakenpreorder.id}">
                                <tr>
                                    <th scope="row">${untakenpreorder.id}</th>
                                    <td>${requestScope.untakencustomers.get(untakenpreordercount).additional}</td>
                                    <td style="width: 20%;">
                                        <button type="button" class="btn btn-block btn-outline-info"
                                                data-toggle="modal"
                                                data-target="#${alphabetuntakenpreorder.charAt(doublecountuntaken)}untakenpreorders">
                                            Se carport
                                        </button>
                                    </td>
                                </tr>
                                <div class="modal fade"
                                     id="${alphabetuntakenpreorder.charAt(doublecountuntaken)}untakenpreorders"
                                     tabindex="-1"
                                     aria-labelledby="${alphabetuntakenpreorder.charAt(doublecountuntaken)}ModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="untakenpreorders">Forespørgelse nr. ${untakenpreorder.id}</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <p>
                                                    Bredde: ${requestScope.untakencarports.get(untakenpreordercount).width}</p>
                                                <p>
                                                    Længde: ${requestScope.untakencarports.get(untakenpreordercount).length}</p>
                                                <p>
                                                    Tagtype/farve: ${requestScope.untakencarports.get(untakenpreordercount).roof}</p>
                                                <p>Tag
                                                    vinkel: ${requestScope.untakencarports.get(untakenpreordercount).roofAngle}
                                                    grader</p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-outline-primary">
                                                    Se tegning
                                                </button>
                                                <button type="button" class="btn btn-outline-secondary"
                                                        data-dismiss="modal">Luk
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:set var="doublecountuntaken" value="${doublecountuntaken + 2}" scope="page"/>
                            </form>
                            <c:set var="untakenpreordercount" value="${untakenpreordercount + 1}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <!-- Untaken preorders END -->

            <!-- Taken preorders START -->
            <c:if test="${requestScope.preorder != null}">
                <h3 style="padding-top: 20px">Aktive forespørgelser</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Evt. besked</th>
                            <th scope="col">Salgsmedarbejder</th>
                            <th scope="col">Vare</th>
                        </tr>
                        </thead>
                        <tbody>

                        <!-- doublecount = used to jump to the next letter in alphabet
                             count  = used to know where in every list we are
                         -->
                        <c:set var="doublecountactive" value="0" scope="page"/>
                        <c:set var="alphabetactivepreorder"
                               value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z"/>
                        <c:set var="countactivepreorder" value="0" scope="page"/>
                        <c:forEach var="takenpreorder" items="${requestScope.preorder}">
                            <form action="Main" method="POST">
                                <input type="hidden" name="target" value="showpreordercarport">
                                <input type="hidden" name="preorderid"
                                       value="${takenpreorder.id}">
                                <tr>
                                    <th scope="row">${takenpreorder.id}</th>
                                    <td>${requestScope.takencustomers.get(countactivepreorder).additional}</td>
                                    <td>Navn :${requestScope.preordersalesmen.get(countactivepreorder).name}, Kontakt
                                        e-mail: ${requestScope.preordersalesmen.get(countactivepreorder).email}</td>
                                    <td style="width: 20%">
                                        <button type="button" class="btn btn-block btn-outline-info"
                                                data-toggle="modal"
                                                data-target="#${alphabetactivepreorder.charAt(doublecountactive)}activepreorders">
                                            Se carport
                                        </button>
                                    </td>
                                </tr>
                                <div class="modal fade"
                                     id="${alphabetactivepreorder.charAt(doublecountactive)}activepreorders"
                                     tabindex="-1"
                                     aria-labelledby="${alphabetactivepreorder.charAt(doublecountactive)}ModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="activepreorders">Forespørgelse nr. ${takenpreorder.id}</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <p>
                                                    Bredde: ${requestScope.preordercarports.get(countactivepreorder).width}</p>
                                                <p>
                                                    Længde: ${requestScope.preordercarports.get(countactivepreorder).length}</p>
                                                <p>
                                                    Tagtype/farve: ${requestScope.preordercarports.get(countactivepreorder).roof}</p>
                                                <p>Tag
                                                    vinkel: ${requestScope.preordercarports.get(countactivepreorder).roofAngle}
                                                    grader</p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-outline-primary">
                                                    Se tegning
                                                </button>
                                                <button type="button" class="btn btn-outline-secondary"
                                                        data-dismiss="modal">Luk
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <c:set var="countactivepreorder" value="${countactivepreorder + 1}" scope="page"/>
                            <c:set var="doublecountactive" value="${doublecountactive + 2}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <!-- Taken preorders END -->
            <!-- Forespørgelser END -->

            <!-- Tilbud START -->
            <h3>Aktive tilbud</h3>
            <c:if test="${requestScope.offer == null}">
                <p>Du har ikke nogle aktive tilbud</p>
            </c:if>

            <c:if test="${requestScope.offer != null}">
                <p>${requestScope.offer}</p>
            </c:if>
            <!-- Tilbud END -->

            <!-- Ordre START -->
            <h3>Afgivet ordre</h3>
            <c:if test="${requestScope.order == null}">
                <p>Du har ikke nogle aftivet ordre</p>
            </c:if>

            <c:if test="${requestScope.order != null}">
                <p>Du har en afgivet ordre</p>
            </c:if>
            <!-- Ordre END -->
        </div>
    </div>

</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>

</body>

</html>