package ex_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	
	public int criar (TO to){
		String sqlInsert = "INSERT INTO Paises(id, nome, populacao, area) VALUES (?, ?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getId());
			stm.setString(2, to.getNome());
			stm.setLong(3, to.getPop());
			stm.setDouble(4, to.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					to.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return to.getId();
	}
	
	public static void atualizar (int id, TO atualizado) {
		String sqlUpdate = "UPDATE Paises SET nome = ?, populacao = ?, area = ? WHERE id = ?";
		try(Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, atualizado.getNome());
			stm.setLong(2, atualizado.getPop());
			stm.setDouble(3, atualizado.getArea());
			stm.setInt(4, id);
			stm.execute();
			conn.close();
			System.out.println(atualizado);
			
		}
			catch(SQLException e) {
				e.printStackTrace();
		}
				
		
	}

	public static void excluir(int id,TO to) {
		String sqlDelete = "DELETE FROM Paises WHERE id = ?";
		try(Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
			conn.close();
			System.out.println(to.getId() + "Excluido");
		
		}
			catch(SQLException e) {
				e.printStackTrace();
		}
		
	}
	
	public static TO carregar (int id) {
		String sqlSelect = "SELECT * FROM Paises WHERE id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			stm.execute();
			try(ResultSet rs = stm.executeQuery();){
				if(rs.next())
					return new TO(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
			}
				catch(SQLException e) {
					e.printStackTrace();
			}
			
			
		}
			catch (SQLException e){
				e.printStackTrace();
		}
		System.out.println("Não achou");
		
		return new TO(id, sqlSelect, id, id);
	}
	
	public static TO getMaiorP() {
		String sqlMaiorPopulacao = "SELECT * FROM Paises ORDER BY populacao DESC LIMIT 1";
		try(Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMaiorPopulacao);) {
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next())
					return new TO(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Não achou");
		return new TO(0, sqlMaiorPopulacao, 0, 0);
		
	}
	
	public static TO getMenorA() {
		String sqlMenorArea = "SELECT * FROM Paises ORDER BY area ASC LIMIT 1";
		try(Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMenorArea);) {
			try(ResultSet rs = stm.executeQuery();){
				if(rs.next())
					return new TO(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Não achou");
		return new TO(0, sqlMenorArea, 0, 0);
	}

	public static Pais[] vetor() {
	String sqlTresPaises = "SELECT * FROM Paises";
	Pais paises[] = new Pais[3];
	try(Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlTresPaises);) {
		try(ResultSet rs = stm.executeQuery()){
			for(int i = 0; i < 3; i++) {
				if(rs.next()) {
					paises[i] = new Pais(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
				}
				else
					System.out.println("Não achou");
			}
			return paises;
		}
			catch(SQLException e) {
				e.printStackTrace();
		}
		
	}
		catch(SQLException e) {
			e.printStackTrace();
	}
	
		return paises;
}

}
