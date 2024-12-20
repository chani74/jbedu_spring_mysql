package com.jbedu.mysql.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jbedu.mysql.dto.BoardDto;

public class BoardDao {

	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jsp_project";
	String username = "root";
	String password = "12345";
	
	// 게시판 글쓰기
	public void boardWrite(String bname, String btitle, String bcontent) {
		String sql = "INSERT INTO mvc_board (bname, btitle, bcontent, bhit)  VALUES (?, ?, ?, 0 ) ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url,username,password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null ) {
					pstmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// 글 모든 리스트 보기
	public List<BoardDto> boardList() {
		String sql = "SELECT * FROM mvc_board ORDER BY bnum desc ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardDto boardDto = null;
		List<BoardDto> boardDtos = new ArrayList<BoardDto>();
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url,username,password);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardDto = new BoardDto(
							rs.getInt("bnum"),
							rs.getString("bname"),
							rs.getString("btitle"),
							rs.getString("bcontent"),
							rs.getString("bdate"),
							rs.getInt("bhit")
						);
				boardDtos.add(boardDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null ) {
					rs.close();
				}
				if(pstmt != null ) {
					pstmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return boardDtos;
		
	}
	// 게시판 글 삭제
	public int boardDelete(String bnum ) {
		String sql = "DELETE FROM mvc_board WHERE BNUM=? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0  ; 
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url,username,password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bnum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null ) {
					pstmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result ;
	}
	
	// 글 모든 리스트 보기
	public BoardDto boardContent(String bnum) {
		String sql = "SELECT * FROM mvc_board WHERE bnum=?  ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardDto boardDto = null;
		
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url,username,password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bnum);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardDto = new BoardDto(
							rs.getInt("bnum"),
							rs.getString("bname"),
							rs.getString("btitle"),
							rs.getString("bcontent"),
							rs.getString("bdate"),
							rs.getInt("bhit")
						);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null ) {
					rs.close();
				}
				if(pstmt != null ) {
					pstmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return boardDto;
		
	}	
}
