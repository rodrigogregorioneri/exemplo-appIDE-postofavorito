(function($app) {

	/**
	 * Função que retorna o formato que será utilizado no componente
	 * capturando o valor do atributo format do elemento, para mais formatos
	 * consulte os formatos permitidos em http://momentjs.com/docs/#/parsing/string-format/
	 * 
	 */
	var patternFormat = function(element) {
		if (element) {
			return $(element).attr('format') || 'DD/MM/YYYY';
		}
		return 'DD/MM/YYYY';
	}

	/**
	 * Em todo elemento que possuir o atibuto as-date será 
	 * aplicado o componente Datetimepicker (http://eonasdan.github.io/bootstrap-datetimepicker/)
	 * 
	 * O componente se adequa de acordo com o formato, definido através do atributo format
	 * espeficado no elemento.
	 * Para data simples use format="DD/MMM/YYYY", para data e hora use format="DD/MM/YYYY HH:mm:ss"
	 * 
	 * @see http://eonasdan.github.io/bootstrap-datetimepicker/
	 */
	app.directive(
			'asDate',
			function() {
				return {
					require : '^ngModel',
					restrict : 'A',
					link : function(scope, element, attrs, ngModel) {
						if (!ngModel) {
							return;
						}

						var format = patternFormat(element);

						var options = {
							format : format,
							locale : 'pt-BR',
							showTodayButton : true,
							useStrict : true,
							tooltips : {
								today : 'Hoje',
								clear : 'Limpar seleção',
								close : 'Fechar',
								selectMonth : 'Selecionar mês',
								prevMonth : 'Mês anterior',
								nextMonth : 'Próximo mês',
								selectYear : 'Selecionar ano',
								prevYear : 'Ano anterior',
								nextYear : 'Próximo ano',
								selectDecade : 'Selecionar década',
								prevDecade : 'Década anterior',
								nextDecade : 'Próxima década',
								prevCentury : 'Século anterior',
								nextCentury : 'Próximo século'
							}
						};

						if (format != 'DD/MM/YYYY') {
							options.sideBySide = true;
						}

						element.datetimepicker(options);

						element.on('dp.change', function() {
							if ($(this).is(":visible"))
								scope.$apply(read);
						});

						ngModel.$render = function() {
							if (ngModel.$viewValue) {
								var dateInMilliseconds = parseInt(
										ngModel.$viewValue, 10);
								var momentDate = moment(dateInMilliseconds);
								if (momentDate.isValid()) {
									element.val(momentDate
											.format(patternFormat(element)));
								} else {
									element.val('');
								}
							} else {
								element.data("DateTimePicker").clear();
								element.val('');
							}
						}

						read();

						function read() {
							var value = element.val();
							var momentDate = moment(value,
									patternFormat(element));
							if (momentDate.isValid())
								ngModel.$setViewValue(momentDate.toDate());
						}
					}
				};
			})

	.directive('pwCheck', [function() {
		'use strict';
		return {
			require : 'ngModel',
			link : function(scope, elem, attrs, ctrl) {
				var firstPassword = '#' + attrs.pwCheck;
				elem.add(firstPassword).on('keyup', function() {
					scope.$apply(function() {
						var v = elem.val() === $(firstPassword).val();
						ctrl.$setValidity('pwmatch', v);
					});
				});
			}
		}
	}])
	
	.directive('moneyMask', function moneyMask($filter, $window) {
    var directive = {
      require: 'ngModel',
      link: link,
      restrict: 'A',
      scope: {
       model : '=ngModel'
      }
    };
    return directive;

    function link(scope, element, attrs, ngModelCtrl) {
      var display, cents;

      ngModelCtrl.$render = function () {
        display = $filter('number')(cents / 100, 2);

        if (attrs.moneyMaskPrepend) {
          display = attrs.moneyMaskPrepend + ' ' + display;
        }

        if (attrs.moneyMaskAppend) {
          display = display + ' ' + attrs.moneyMaskAppend;
        }

        element.val(display);
      }

      scope.$watch('model', function onModelChange(newValue) {
        newValue = parseFloat(newValue) || 0;

        if (newValue !== cents) {
          cents = Math.round(newValue * 100);
        }

        ngModelCtrl.$viewValue = newValue;
        ngModelCtrl.$render();
      });

      element.on('keydown', function (e) {
        if (e.keyCode === 8) {
          cents = parseInt(cents.toString().slice(0, -1)) || 0;

          ngModelCtrl.$setViewValue(cents / 100);
          ngModelCtrl.$render();
          scope.$apply();
          e.preventDefault();
        }
      });

      element.on('keypress', function (e) {
        var char = String.fromCharCode(e.keyCode);
        e.preventDefault();

        if (char.search(/[0-9\-]/) === 0) {
          cents = parseInt(cents + char);
        }
        else {
          return false;
        }

        //if(e.srcElement.selectionEnd != e.srcElement.selectionStart) {
          //ngModelCtrl.$setViewValue(parseInt(char) / 100);
        //}
        //else {
          //ngModelCtrl.$setViewValue(cents / 100);
      //}
        ngModelCtrl.$render();
        scope.$apply();
      });
    }
  }
)
	
	
.directive ('placa',[function() {
	var ehPlacaValida = function(placa) {
		var er = /[a-z]{3}-?\d{4}/gim;
		er.lastIndex = 0;
		return er.test(placa);
    }
	}])


			/**
			 * Validação de campos CPF e CNPJ,
			 * para utilizar essa diretiva, adicione o atributo valid com o valor 
			 * do tipo da validação (cpf ou cnpj). Exemplo <input type="text" valid="cpf"> 
			 */
			.directive(
					'valid',
					function() {
						return {
							require : '^ngModel',
							restrict : 'A',
							link : function(scope, element, attrs, ngModel) {
								var validator = {
									'cpf' : CPF,
									'cnpj' : CNPJ
								};

								ngModel.$validators[attrs.valid] = function(
										modelValue, viewValue) {
									var value = modelValue || viewValue;
									var fieldValid = validator[attrs.valid]
											.isValid(value);
									if (!fieldValid) {
										element[0]
												.setCustomValidity(element[0].dataset['errorMessage']);
									} else {
										element[0].setCustomValidity("");
									}
									return (fieldValid || !value);
								};
							}
						}
					})
}(app));