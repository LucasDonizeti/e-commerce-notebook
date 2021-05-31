var ctx = document.getElementById('grafico').getContext("2d");
var grafico = new Chart(ctx, {
  type: 'line'
})
const APIURI = window.location.origin

$("#pesquisar").click(function () {
  var inicio = document.getElementById('inicio').value
  var fim = document.getElementById('fim').value
  limparErro()

  if (inicio.length < 3 || fim.length < 3) {
    erro("insira as duas datas")
    return
  }

  if (inicio < fim) {
    $.ajax({
      type: 'GET',
      url: APIURI + "/estatistica/findVendasPorProdutoAndPeriodo",
      data: { dataInicio: inicio, dataFim: fim },
      async: true,
      statusCode: {
        200: function (data) {
          gerarTabela(data);
        }
      }
    });
  } else {
    erro("data incícial é maior que a final!")
  }
})

function erro(erro) {
  document.getElementById('erro').innerHTML = erro
}
function limparErro() {
  document.getElementById('erro').innerHTML = ''
}


function gerarTabela(data) {
  grafico.destroy();
  grafico = new Chart(ctx, {
    type: 'line',
    data: data,
    options: {
      responsive: true,
      interaction: {
        mode: 'index',
        intersect: false,
      },
      stacked: false,
      plugins: {
        title: {
          display: true,
          text: 'Volume de vendas por produto'
        }
      },
      scales: {
        y: {
          type: 'linear',
          display: true,
          position: 'left',
        },
        y1: {
          type: 'linear',
          display: true,
          position: 'right',
          grid: {
            drawOnChartArea: false,
          },
        },
      }
    },
  })
}
