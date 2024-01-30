import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  valortransacao: number = 0;
  data: Date | null = null;
  categoria: string = "";
  valortransacaoLote: number[] = [0, 0, 0, 0, 0];
  dataLote: (Date | null)[] = [null, null, null, null, null];
  categoriaLote: string[] = ['', '', '', '', ''];
  formulario: boolean = false;
  transacaoSelecionada: any = null; 
  transacoes: any[] = [];
  somaPorCategoria: number | null = null; 
  categoriaSoma: number | null = null; 
  dataFiltragem: string = '';
  somaPorData: number | null = null;
  linhasTransacao: any[] = [{ valor: 0, categoria: '' , data: null }, { valor: 0, categoria: '' , data: null },
  { valor: 0, categoria: '' , data: null },{ valor: 0, categoria: '' , data: null },{ valor: 0, categoria: '' , data: null }];

  public urlbd = "http://localhost:8080/transacao/inserir";
  public urlBuscaTransacoes = "http://localhost:8080/transacao";
  public urlSomaPorCategoria = "http://localhost:8080/transacao/somartransacoes?id=";

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.buscarTransacoes();
  }

  salvar(parametro: "unica" | "massa"): void {
    if (parametro === 'unica') {
      this.salvarTransacaoUnica();
    } else if (parametro === 'massa') {
      this.salvarTransacoesMassa();
    } else {
      console.error('Parâmetro inválido:', parametro);
    }
  }
  
  private salvarTransacaoUnica(): void {
    if (!this.categoria) {
      alert('Por favor, selecione uma categoria.');
      return;
    }
  
    console.log('Categoria selecionada:', this.categoria);
    const novaTransacao = {
      valor: this.valortransacao,
      categoria: { id: Number(this.categoria) },
      data: this.data
    };
  
    this.transacoes.push({ ...novaTransacao });
  
    console.log('Lista de transações atualizada:', this.transacoes);
  
    this.http.post(this.urlbd, this.transacoes).subscribe(
      (response) => {
        console.log('Dados salvos com sucesso!', response);
        alert('Dados salvos com sucesso!');
  
        this.valortransacao = 0;
        this.data = null;
        this.categoria = '';
        this.transacoes = [];
  
        this.buscarTransacoes();
      },
      (error) => {
        console.error('Erro ao salvar os dados:', error);
        alert('Erro ao salvar os dados. Verifique o console para mais informações.');
  
        console.log('Erro detalhado:', error.error);
  
        console.log('Corpo da solicitação:', this.transacoes);
      }
    );
  }
  
  private salvarTransacoesMassa(): void {
    if (!this.categoriaLote) {
      alert('Por favor, selecione uma categoria para todas as transações em lote.');
      return;
    }

    const transacoesEmMassa = this.linhasTransacao.filter((valor) => valor.categoria !== "").map((linha) => ({
      valor: linha.valor,
      categoria: { id: Number(linha.categoria) },
      data: linha.data
    }));
  
    console.log('Transações em massa:', transacoesEmMassa);
  
    this.http.post(this.urlbd, transacoesEmMassa).subscribe(
      (response) => {
        console.log('Dados salvos com sucesso!', response);
        alert('Dados salvos com sucesso!');
  
        this.buscarTransacoes();
      },
      (error) => {
        console.error('Erro ao salvar os dados:', error);
        alert('Erro ao salvar os dados. Verifique o console para mais informações.');
  
        console.log('Erro detalhado:', error.error);
  
        console.log('Corpo da solicitação:', transacoesEmMassa);
      }
    );
  }

  removerTransacao(index: number): void {
    this.transacoes.splice(index, 1);
  }

  buscarTransacoes(): void {
    this.http.get(this.urlBuscaTransacoes).subscribe(
      (response: any) => {
        this.transacoes = response;
        console.log('Todas as transações:', this.transacoes);
      },
      (error) => {
        console.error('Erro ao buscar todas as transações:', error);
      }
    );
  }

  atualizarTransacao(transacao: any): void {
    this.formulario = true;
    this.transacaoSelecionada = {
      id: transacao.id,
      valor: transacao.valor,
      categoria: { 
        id: Number(transacao.categoria.id)
      },
      data: transacao.data
    };

  }

  confirmarAtualizacao(): void {
    this.http.post('http://localhost:8080/transacao/atualizar', this.transacaoSelecionada).subscribe(
      (response) => {
        console.log('Transação atualizada com sucesso!', response);
        alert('Transação atualizada com sucesso!');
        this.formulario = false;
        this.buscarTransacoes();
      },
      (error) => {
        console.error('Erro ao atualizar a transação:', error);
        alert('Erro ao atualizar a transação. Verifique o console para mais informações.');
        console.log('Resposta do servidor:', error.error);
        this.formulario = false;
      }
    );
  }

  deletarTransacao(id: number): void {
    const urlDeletarPorId = `http://localhost:8080/transacao?id=${id}`;

    if (confirm(`Tem certeza que deseja excluir a transação com ID ${id}?`)) {
      this.http.delete(urlDeletarPorId).subscribe(
        (response) => {
          console.log(`Transação com ID ${id} excluída com sucesso!`, response);
          alert(`Transação com ID ${id} excluída com sucesso!`);
          this.buscarTransacoes();
        },
        (error) => {
          console.error(`Erro ao excluir a transação com ID ${id}:`, error);
          alert(`Erro ao excluir a transação com ID ${id}. Verifique o console para mais informações.`);
        }
      );
    }
  }

  deletarTodasTransacoes(): void {
    const urlDeletarTudo = `http://localhost:8080/transacao/apagartudo`;
    if (confirm('Tem certeza que deseja excluir todas as transações?')) {
      this.http.delete(urlDeletarTudo).subscribe(
        (response) => {
          console.log('Todas as transações foram excluídas com sucesso!', response);
          alert('Todas as transações foram excluídas com sucesso!');
          this.buscarTransacoes();
        },
        (error) => {
          console.error('Erro ao excluir todas as transações:', error);
          alert('Erro ao excluir todas as transações. Verifique o console para mais informações.');
        }
      );
    }
  }

  somarTransacoesPorCategoria(): void {
    if (!this.categoriaSoma) {
      alert('Selecione uma categoria');
      return;
    }
  
    this.http.get(`http://localhost:8080/transacao/somartransacoes?id=${this.categoriaSoma}`)
      .subscribe(
        (response: any) => {
          this.somaPorCategoria = response;
          console.log(`Soma das transações para a categoria ${this.categoriaSoma}: ${this.somaPorCategoria}`);
        },
        (error) => {
          console.error('Erro ao calcular a soma:', error);
          alert('Erro ao calcular a soma. Verifique o console para mais informações.');
        }
    );
  }
}
