package ru.oksei.talisman.simpleMoney.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.talisman.simpleMoney.Models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByPerson_PersonId(int personId);
    List<Category> findAllByCategoryTypeAndPerson_PersonId(String categoryType, int personId);
    Category findByCategoryId(int categoryId);
}
