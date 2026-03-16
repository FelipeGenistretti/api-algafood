package com.jpa_exemplo.jpa_exemplo.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeEmUsoException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.i18n.LocaleContextResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(e);

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if(rootCause instanceof PropertyBindingException){
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        String detail = "O corpo da requisição está inválido. Verifique um erro de sintaxe";
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        HttpStatus httpStatus = HttpStatus.valueOf(status.value());

        Problema problema = createProblemBuilder(httpStatus, problemType, detail)
                .build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        HttpStatus httpStatus = HttpStatus.valueOf(status.value());

        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindingResult = ex.getBindingResult();

        List<Problema.Field> problemFields = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> {

                    String message = messageSource.getMessage(
                            fieldError,
                            LocaleContextHolder.getLocale()
                    );

                    return Problema.Field.builder()
                            .name(fieldError.getField())
                            .userMessage(message)
                            .build();
                })
                .toList();

        Problema problema = createProblemBuilder(httpStatus, problemType, detail)
                .fields(problemFields)
                .build();

        return handleExceptionInternal(ex, problema, headers, httpStatus, request);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        HttpStatus httpStatus = HttpStatus.valueOf(status.value());
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
        Problema problema = createProblemBuilder(httpStatus, problemType, detail).build();

        return super.handleExceptionInternal(ex, problema,headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        if(e instanceof MethodArgumentTypeMismatchException){
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) e, headers, status, request);
        }

        return super.handleTypeMismatch(e, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    )
    {
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO ;
        HttpStatus httpStatus = HttpStatus.valueOf(status.value());
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s',que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.", e.getName(), e.getValue(), e.getRequiredType().getSimpleName());
        Problema problema = createProblemBuilder(httpStatus,problemType, detail).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }


    private ResponseEntity<Object> handlePropertyBindingException(
            PropertyBindingException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    )
    {

        String path = e.getPropertyName();

        String detail = String.format("O corpo da requisição apresenta um campo inválido: '%s'. Verifique a existência de um campo extra", path);
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        HttpStatus httpStatus = HttpStatus.valueOf(status.value());
        Problema problema = createProblemBuilder(httpStatus, problemType, detail).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(
            InvalidFormatException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request){

        String path = e.getPath().stream().map(ref->ref.getFieldName()).collect(Collectors.joining("."));

        HttpStatus httpStatus = HttpStatus.valueOf(status.value());
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'", path, e.getValue(), e.getTargetType().getSimpleName());

        Problema problema = createProblemBuilder(httpStatus, problemType, detail)
                .build();

        return handleExceptionInternal(e, problema,new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(
            Exception e,
            WebRequest request
    )
    {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = String.format("Ocorreu um erro inesperado no sistema. Se o problema persistir, entre em contato com o administrador do sistema");
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        Problema problema = createProblemBuilder(status, problemType, detail).build();
        e.printStackTrace();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException e,
            WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = e.getMessage();
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;

        Problema problema = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(
                e,
                problema,
                new HttpHeaders(),
                status,
                request
        );
    }


    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = e.getMessage();
        ProblemType problemType = ProblemType.NEGOCIO_EXCEPTION;

        Problema problema = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Problema problema = Problema.builder()
                .title("O tipo de mídia não é aceito")
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }


    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e , WebRequest request)
    {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = e.getMessage();
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO_EXCEPTION;


        Problema problema = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if(body == null){
            body = Problema.builder().title(ex.getMessage()).status(statusCode.value()).build();
        } else if(body instanceof String){
            body = Problema.builder().title(ex.getMessage()).status(statusCode.value()).build();
        }


        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private Problema.ProblemaBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problema.builder().status(status.value()).type(problemType.getUri()).title(problemType.getTitle()).detail(detail);
    }

}
