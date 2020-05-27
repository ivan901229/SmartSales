<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="UTF-8" import="java.util.*, java.sql.*,java.text.*,java.awt.*,java.io.*, javax.naming.*, javax.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%	
	Connection conn = null;
	try {
	Context context = new InitialContext();
	DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
	conn = ds.getConnection();
	} catch (NamingException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	String sql = "SELECT photo FROM member_overview WHERE number = ?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	String memberNo = request.getParameter("memberNo");
	System.out.println(memberNo);
	stmt.setString(1, memberNo);
	ResultSet rs = stmt.executeQuery();
	
	if(rs.next()) {
		Blob b = rs.getBlob("photo");
		long size = b.length();
		//out.print(size);
		byte[] bs = b.getBytes(1, (int)size);
		response.setContentType("image/jpeg");
		OutputStream outs = response.getOutputStream();
		outs.write(bs);
		outs.flush();
		rs.close();
		}
		else {
		rs.close();
	}
%>
</body>
</html>