package com;

import com.jeesite.modules.project.dao.NewProjectDao;
import com.jeesite.modules.project.entity.NewProject;
import com.jeesite.modules.project.service.NewProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewProjectServiceTest {

    @InjectMocks
    private NewProjectService newProjectService;
    @Mock
    private NewProjectDao newProjectDao;

    @Test
    public void buildSaleContractCode_IdNull() {
        NewProject newProject = new NewProject();
        newProject.setProjectCode("PM2023-10");
        NewProject project = newProjectService.buildSaleContractCode(newProject);
        assertEquals("PM2023-10", project.getProjectCode());
    }

    @Test
    public void buildSaleContractCode_callNull() {
        NewProject newProject = new NewProject();
        List<NewProject> list = new ArrayList<>();
        when(newProjectDao.findList(newProject)).thenReturn(list);
        NewProject project = newProjectService.buildSaleContractCode(newProject);
        assertEquals("PM2023-01", project.getProjectCode());
    }

    @Test
    public void buildSaleContractCode_callNotNull() {
        NewProject newProject = new NewProject();
        List<NewProject> list = new ArrayList<>();
        NewProject newProjectTest = new NewProject();
        newProjectTest.setProjectCode("PM2023-10");
        list.add(newProjectTest);
        when(newProjectDao.findList(newProject)).thenReturn(list);
        NewProject project = newProjectService.buildSaleContractCode(newProject);
        assertEquals("PM2023-11", project.getProjectCode());
    }
}
