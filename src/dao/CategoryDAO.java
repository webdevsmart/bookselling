package dao;

import java.util.List;

import model.Category;

public interface CategoryDAO {

	// add category
	public void addCategory(Category c);

	public void delCategory(int ma_the_loai);

	public List<Category> getList();

	public Category getCategory(int ma_the_loai);
	
	public void updateCategory(Category c);

}
