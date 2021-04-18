<!DOCTYPE html>
<html>
  <head>
    <title>Insert title here</title>
  </head>
  <body>

    <%! String filepath = this.getServletContext().getRealPath(getServletInfo())
            + "\\src\\main\\webapp\\WEB-INF\\drugs.tsv"; %>
    <%--! List<String> lines=Files.readAllLines(Path.of(filepath)); --%>
    <%! %>

    System.out.println(lines.get(0));
    for(String s:lines.subList(1,10))


  </body>
</html>
