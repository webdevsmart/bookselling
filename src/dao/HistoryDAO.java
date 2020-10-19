package dao;

import java.util.List;

import model.History;

public interface HistoryDAO {
	
	public void addHistory(History h);
	
	public List<History> getList(int user_id);

}
