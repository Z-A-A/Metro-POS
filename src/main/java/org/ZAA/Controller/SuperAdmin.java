package org.ZAA.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SuperAdmin
{
    private String name;
    private String email;
    private String password;
    private METRO_POS_MAIN_CONTROLLER_CODE mainController;

    public SuperAdmin()
    {

    }

    public SuperAdmin(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMainController(METRO_POS_MAIN_CONTROLLER_CODE mainController) {
        this.mainController = mainController;
    }

    @PostMapping("/createBranch")
    public boolean createBranch(@RequestParam String branchCode, @RequestParam String name, @RequestParam String city, @RequestParam Boolean isActive, @RequestParam String address, @RequestParam String phone) {
        Branch newBranch = new Branch(branchCode, name, city, isActive, address, phone);
        BranchManagement newBranchManagement = new BranchManagement(newBranch);
        mainController.getBranches().add(newBranchManagement);
        //UPDATE IN DB HERE
        System.out.println("BRANCH CREATED SUCCESSFULLY: " + name);
        return true;
    }
}