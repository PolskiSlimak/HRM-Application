package com.paw.hrmApp.service;

import com.paw.hrmApp.dao.FinderDAO;
import com.paw.hrmApp.dto.EmployeeHistoryDTO;
import com.paw.hrmApp.dto.EmployeeHistoryStatsDTO;
import com.paw.hrmApp.exception.ResourceNotFoundException;
import com.paw.hrmApp.mapper.EmployeeMapper;
import com.paw.hrmApp.model.EmployeeHistoryEntity;
import com.paw.hrmApp.repository.EmployeeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeHistoryService {
    private final EmployeeHistoryRepository employeeHistoryRepository;
    private final FinderDAO finderDAO;

    public List<EmployeeHistoryDTO> getEmployeeHistory() {
        List<EmployeeHistoryEntity> employeeHistoryEntityList = employeeHistoryRepository.findAll();
        return mapToDTOList(employeeHistoryEntityList);
    }

    public EmployeeHistoryDTO getParticularEmployeeHistory(Long id) {
        EmployeeHistoryEntity employeeHistoryEntity = finderDAO.getHistoryEntity(id);
        return EmployeeMapper.mapToEmployeeHistoryDTO(employeeHistoryEntity);
    }

    public EmployeeHistoryStatsDTO getStatistics(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateFrom;
        try {
            dateFrom = formatter.parse(date);
        } catch (ParseException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        Integer numberOfEmployees = employeeHistoryRepository.findEmployeesCount(dateFrom);
        return new EmployeeHistoryStatsDTO(numberOfEmployees);
    }

    public void deleteEmployeeHistory(Long id) {
        EmployeeHistoryEntity employeeHistoryEntity = finderDAO.getHistoryEntity(id);
        employeeHistoryRepository.delete(employeeHistoryEntity);
    }

    private List<EmployeeHistoryDTO> mapToDTOList(List<EmployeeHistoryEntity> employeeEntityList) {
        List<EmployeeHistoryDTO> employeesDTO = new ArrayList<>();
        for (EmployeeHistoryEntity employeeEntity : employeeEntityList) {
            EmployeeHistoryDTO employeeDTO = EmployeeMapper.mapToEmployeeHistoryDTO(employeeEntity);
            employeesDTO.add(employeeDTO);
        }
        return employeesDTO;
    }
}
