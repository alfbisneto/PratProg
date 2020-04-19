package ex_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pais {
	
	public int id;
	private String nome;
	private long populacao;
	private double area;
	
	static {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		} catch (ClassNotFoundException e) {
		throw new RuntimeException(e);
			}
	}
	
	public static Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/Aula1?user=root&password=pedro&useTimezone=true&serverTimezone=UTC");
		}
	
	public Pais() {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	public Pais(int id, String nome, long populacao, double area) {
	}

	public int getId() {
		return id;
		}
	
	public void setId(int id) {
		this.id = id;
		}
	
	public String getNome() {
		return nome;
		}
	
	public void setNome(String nome) {
		this.nome = nome;
		}
	
	public long getPop() {
		return populacao;
		}
	
	public void setPop(long populacao) {
		this.populacao = populacao;
		}
	
	public double getArea() {
		return area;
		}
	
	public void setArea(double area) {
		this.area = area;
		}
	
	@Override
	public String toString() {
	return"Pais [id=" + id + ", nome=" + nome + ", populacao=" + populacao	+ ", area=" + area + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (populacao != other.populacao)
			return false;
		if (id != other.id)
			return false;
		if (area != other.area) 
			return false;
		return true;
	}
	
	public static int criar (Pais pais){
		String sqlInsert = "INSERT INTO Paises(id, nome, populacao, area) VALUES (?, ?, ?, ?)";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, pais.getId());
			stm.setString(2, pais.getNome());
			stm.setLong(3, pais.getPop());
			stm.setDouble(4, pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}
	
	public static void atualizar (int id, Pais atualizado) {
		String sqlUpdate = "UPDATE Paises SET nome = ?, populacao = ?, area = ? WHERE id = ?";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, atualizado.getNome());
			stm.setLong(2, atualizado.getPop());
			stm.setDouble(3, atualizado.getArea());
			stm.setInt(4, id);
			stm.execute();
			System.out.println(atualizado);
			
		}
			catch(SQLException e) {
				e.printStackTrace();
		}
				
		
	}

	public static void excluir(int id) {
		String sqlDelete = "DELETE FROM Paises WHERE id = ?";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
			conn.close();
			System.out.println(id + "Excluido");
		
		}
			catch(SQLException e) {
				e.printStackTrace();
		}
		
	}
	
	public static Pais carregar (int id) {
		Pais pais = new Pais();
		pais.setId(id);
		String sqlSelect = "SELECT * FROM Paises WHERE Paises.id = ?";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPop(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPop(0);
					pais.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	public static Pais getMaiorP() {
		Pais pais = new Pais();
		String sqlMaiorPopulacao = "SELECT * FROM Paises ORDER BY populacao DESC LIMIT 1";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMaiorPopulacao);) {
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next())
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPop(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
					return pais;
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		conn.close();
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Não achou");
		return new Pais();
		
	}
	
	public static Pais getMenorA() {
		Pais pais = new Pais();
		String sqlMenorArea = "SELECT * FROM Paises ORDER BY area ASC LIMIT 1";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMenorArea);) {
			try(ResultSet rs = stm.executeQuery();){
				if(rs.next())
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPop(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
					return pais;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			conn.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Não achou");
		return new Pais();
	}

	public static Pais[] vetor() {
	String sqlTresPaises = "SELECT * FROM Paises";
	Pais paises[] = new Pais[3];
	try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlTresPaises);) {
		try(ResultSet rs = stm.executeQuery()){
			for(int i = 0; i < 3; i++) {
				if(rs.next()) {
					paises[i] = new Pais();
				}
				else
					System.out.println("Não achou");
			}
			return paises;
		}
			catch(SQLException e) {
				e.printStackTrace();
		}
		
		conn.close();
		
	}
		catch(SQLException e) {
			e.printStackTrace();
	}
	
		return paises;
}

}
