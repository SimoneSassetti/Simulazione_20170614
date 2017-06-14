package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exibitions;
import it.polito.tdp.artsmia.model.ExibitionsPair;

public class ArtsmiaDAO {

	public List<ArtObject> listObject(Map<Integer, ArtObject> mappa) {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				ArtObject a=new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				result.add(a);
				mappa.put(res.getInt("object_id"), a);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Year> getAnni() {
		
		String sql="select distinct begin\r\n" + 
				"from exhibitions\r\n" + 
				"order by begin";
		
		List<Year> anni = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				anni.add(Year.of(res.getInt("begin")));
			}

			conn.close();
			return anni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Exibitions> getMostre(Year y, Map<Integer, Exibitions> mappa) {
		
		String sql="select *\r\n" + 
				"from exhibitions\r\n" + 
				"where begin>=?";
		
		List<Exibitions> mostre = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, y.getValue());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id=rs.getInt("exhibition_id");
				Exibitions e=mappa.get(id);
				if(e==null){
					Exibitions nuovo=new Exibitions(id,rs.getString("exhibition_department"),rs.getString("exhibition_title"),Year.of(rs.getInt("begin")),Year.of(rs.getInt("end")));
					mappa.put(id, nuovo);
					mostre.add(nuovo);
				}else{
					mostre.add(e);
				}
			}
			conn.close();
			return mostre;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ExibitionsPair> getCoppie(Year y, Map<Integer, Exibitions> mappa){
		
		String sql="select e1.exhibition_id as id_e1,e2.exhibition_id as id_e2\n" + 
				"from exhibitions as e1,exhibitions as e2\n" + 
				"where e1.begin<e2.begin and e2.begin<e1.end and e1.begin>=?";
		
		List<ExibitionsPair> coppie = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, y.getValue());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id_e1=rs.getInt("id_e1");
				int id_e2=rs.getInt("id_e2");
				Exibitions e1=mappa.get(id_e1);
				Exibitions e2=mappa.get(id_e2);
				
				if(e1!=null && e2!=null){
					ExibitionsPair pair=new ExibitionsPair(e1,e2);
					coppie.add(pair);
				}
			}
			conn.close();
			return coppie;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ArtObject> getOpereMostra(Exibitions e,Map<Integer, ArtObject> mappa){
		
		String sql="select o.object_id\r\n" + 
				"from objects as o,exhibition_objects as eo, exhibitions as e\r\n" + 
				"where o.object_id=eo.object_id and e.exhibition_id=eo.exhibition_id and e.exhibition_id=?";
		
		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, e.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				ArtObject opera=mappa.get(res.getInt("object_id"));
				result.add(opera);
			}

			conn.close();
			return result;
		} catch (SQLException evento) {
			// TODO Auto-generated catch block
			evento.printStackTrace();
			return null;
		}
	}
}
