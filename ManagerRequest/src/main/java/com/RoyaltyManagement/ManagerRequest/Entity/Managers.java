package com.RoyaltyManagement.ManagerRequest.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "managers")
public class Managers {

    @Id
    @Column(name = "manager_id")
    private Long managerid;

    @Column(name = "manager_name", nullable = false)
    private String managerName;

    @Column(name = "company", nullable = false)
    private String company;

    public Long getManagerid() {
        return managerid;
    }

    public void setManagerid(Long managerid) {
        this.managerid = managerid;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }




}
