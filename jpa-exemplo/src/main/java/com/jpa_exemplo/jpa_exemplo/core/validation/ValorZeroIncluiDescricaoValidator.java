package com.jpa_exemplo.jpa_exemplo.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;

import java.beans.Beans;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descrocaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraint) {

        this.valorField = constraint.valorField();
        this.descrocaoField = constraint.descrocaoField();
        this.descricaoObrigatoria = constraint.descricaoObrigatoria();

    }

    @Override
    public boolean isValid(Object objetoValidacao, ConstraintValidatorContext constraintValidatorContext) {
        boolean valido = true;
        try{
            //BigDecimal valor = BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField).getReadMethod().invoke(objetoValidacao);
            //String descriacao =BigDecimal valor = BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField).getReadMethod().invoke(objetoValidacao);
        } catch (Exception e){
            throw new ValidationException(e);
        }

        return valido;
    }
}
