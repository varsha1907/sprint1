package com.mycompany.dao;

import com.mycompany.model.Company;

public interface CompanyDao {
    /**
     * Retrieves a {@link Company} with all its products by company id
     *
     * @param id company id
     * @return company with all its products
     */
    Company findByIdFetchProducts(Long id);
}
