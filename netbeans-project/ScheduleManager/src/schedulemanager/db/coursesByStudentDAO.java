package schedulemanager.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import schedulemanager.db.ShiftDAO;
import schedulemanager.model.Swap;


public class coursesByStudentDAO implements Map<String, ShiftDAO> {
    
    private Connection conn;
    ShiftDAO ShiftDAO = new ShiftDAO();
    
    /**
     * Apagar todos os alunos
     * NAO MEXI
     */
    @Override
    public void clear () {
        throw new NullPointerException("clear not implemented!");
        /*
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM HashMap<String, Swap> where id>0");
        } catch (Exception e) {
            //runtime exeption!
            throw new NullPointerException(e.getMessage()); 
        } finally {
            Connect.close(conn);
        }
        */
    }
    
    /**
     * Verifica se um número de aluno existe na base de dados
     * @param key
     * @return
     * @throws NullPointerException 
     * 
     * NAO MEXI
     */
    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        boolean r = false;
        try {
            conn = Connect.connect();
            String sql = "SELECT `id` FROM HashMap<String, Swap> WHERE `id`=?;";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, key.toString());
            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }
        return r;
    }
    
    /**
     * Verifica se um aluno existe na base de dados
     * 
     * Esta implementação é provisória. Devia testar todo o objecto e não apenas a chave.
     * 
     * @param value
     * @return 
     * 
     * NAO MEXI
     */
    
    @Override
    public boolean containsValue(Object value) {
        throw new NullPointerException("not implemented!");
    }
    
    
    /*
    *NAO MEXI
    */
    @Override
    public Set<Map.Entry<String, ShiftDAO>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Aluno>> entrySet() not implemented!");
    }
    
    
    /*
    *NAO MEXI
    */    
    @Override
    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }
    
    /**
     * Obter um aluno, dado o seu número
     * @param key
     * @return 
     * 
     * NAO MEXI
     */
    @Override
    public ShiftDAO get(Object key) {
        ShiftDAO st = null;
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM HashMap<String, Swap> WHERE id=?");
            stm.setString(1, key.toString());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                st = new ShiftDAO();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return st;
    }
    
    
    /*
    *NAO MEXI
    */
    @Override
    public int hashCode() {
        return this.conn.hashCode();
    }
    
    /**
     * Verifica se existem entradas
     * @return 
     * 
     * NAO MEXI
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    
    /*
    * NAO MEXI
    */
    
    @Override
    public Set<String> keySet() {
        throw new NullPointerException("Not implemented!");
    }
    
    /**
     * Insere um aluno na base de dados
     * @param key
     * @param value
     * @return 
     * 
     * DONE
     */
    
    @Override
    public ShiftDAO put(String key, ShiftDAO value) {
        ShiftDAO stud = null;
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Aluno\n" +
                "VALUES (?)");
            stm.setString(1, ShiftDAO);

            stm.executeUpdate();
            
            stud = value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return stud;
    }

    /**
     * Por um conjunto de alunos na base de dados
     * @param t 
     * 
     * NAO MEXI
     */
    @Override
    public void putAll(Map<? extends String,? extends ShiftDAO> t) {
        for(ShiftDAO s : t.values()) {
            put(s.getId(), s);
        }
    }
    
    /**
     * Remover um aluno, dado o seu numero
     * @param key
     * @return 
     * 
     * NAO MEXI
     */
    @Override
    public ShiftDAO remove(Object key) {
        ShiftDAO al = this.get(key);
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("delete from HashMap<String, Swap> where id = ?");
            stm.setInt(1, (Integer)key);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }
        return al;
    }
    
    /**
     * Retorna o número de entradas na base de dados
     * @return 
     * 
     * NAO MEXI
     */
    @Override
    public int size() {
        int i = 0;
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT count(*) FROM HashMap<String, Swap>");
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
        finally {
            Connect.close(conn);
        }
        return i;
    }
    
    /**
     * Obtém todos os alunos da base de dados
     * @return 
     * 
     * NAO MEXI
     */
    @Override
    public Collection<ShiftDAO> values() {
        Collection<HashMap<String, Swap>> col = new HashSet<HashMap<String, Swap>>();
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM HashMap<String, Swap>");
            while (rs.next()) {
                col.add(new HashMap<String, Swap>(rs.getString("Id") ,rs.getString("Name"), rs.getString("RegTeacher_Id")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            Connect.close(conn);
        }
        return col;
    }
    
}