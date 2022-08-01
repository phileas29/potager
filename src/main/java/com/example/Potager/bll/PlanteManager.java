package com.example.Potager.bll;

import org.springframework.stereotype.Service;

import com.example.Potager.bo.Plante;
import com.example.Potager.dal.PlanteDAO;


@Service
public class PlanteManager extends CRUDManager<Plante, PlanteDAO> {

}
