package aulaDataHoraJava;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

public class Program {

	public static void main(String[] args) {
		
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		//instaciar uma data de agora - o momento atual
		//pega a data do sistema local
		LocalDate d01 = LocalDate.now();
		System.out.println("D01 = " + d01);
		
		//com a data
		LocalDateTime d02 = LocalDateTime.now();
		System.out.println("D02 = " + d02);
		
		//com instant - tem o time zone no final. Como é uma data/hora global, gera no horário de londres (Z)
		//posso querer converter esse horário para o valor no meu fuso local
		Instant d03 = Instant.now();
		System.out.println("D03 - é data/hora global = " + d03);
		
		//gerar data/hora a partir de texto ISO 8601 - faço um parse e coloco o texto com a data a ser convertida para data
		LocalDate d04 = LocalDate.parse("2022-07-20");
		System.out.println("D04 - converte de texto para data = " + d04);
		
		//com hora
		LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:26");
		System.out.println("D05 - converte de texto para data/hora = " + d05);
		
		//Com instant - hora global - acrescentar o fuso no final a partir de um texto no padrão iso 8601
		Instant d06 = Instant.parse("2022-07-20T01:30:26Z");
		System.out.println("D06 - converte de texto para data/hora e acrescentar o timezone no final = " + d06);
		
		//Com instant - hora global - acrescentar o fuso no final a partir de um texto no padrão iso 8601 e especificando 
		//um timezone diferente do zulu
				Instant d07 = Instant.parse("2022-07-20T01:30:26-03:00");//-03:00 == atrasado 3 horas do hor de londres
				System.out.println("D07 - converte de texto para data/hora e especifica o timezone diferente do Z = " + d07);
				
		//texto com horário customizado. Para isso, tenho que instanciar antes o objeto de formatação datetimeformatter
				LocalDate d08 = LocalDate.parse("20/03/2022", fmt1);
				System.out.println("D08 - passa um tipo de data customizado e um segundo arg com o critério de formatação = " + d08);
				
				//acrescentando padrão de time
				LocalDateTime d09 = LocalDateTime.parse("20/03/2022 01:30", fmt2);
				System.out.println("D09 -  critério de formatação de hora = " + d09);
				
				//posso escolher instanciar o formater direto no argumento
				LocalDateTime d10 = LocalDateTime.parse("20/03/2022 01:30", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
				System.out.println("D10 -  instancia o formater dentro dos parâmetros = " + d10);
				
				//instanciando com dia, mês e ano isolados
				LocalDate d11 = LocalDate.of(2022, 7, 20);
				System.out.println("D11 - instancia com dia mes e ano isolados = " + d11);
				
				//instanciando com dia, mês e ano isolados acrescentando horas
				LocalDateTime d12 = LocalDateTime.of(2022, 7, 20, 1, 30);
				System.out.println("D12 - instancia com dia mes e ano isolados e HOras = " + d12);
				
				
				//CONVERTENDO DE DATA/HORA PARA TEXTO
				
				LocalDate dt1 = LocalDate.parse("2022-07-20");
				LocalDateTime dt2 = LocalDateTime.parse("2022-07-20T01:30:26");
				Instant dt3 = Instant.parse("2022-07-20T01:30:26Z");
				
				DateTimeFormatter fmtT1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				DateTimeFormatter fmtT2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				DateTimeFormatter fmT3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());//pega o fuso do computador local
				//usando o nome do padrão no lugar do método ofPattern
				DateTimeFormatter fmtT4 = DateTimeFormatter.ISO_DATE_TIME;
				//para hora global - instant
				DateTimeFormatter fmtT5 = DateTimeFormatter.ISO_INSTANT;
				
				System.out.println("dt1 = " + dt1.format(fmtT1));
				//outra opção
				System.out.println("dt1 = " + fmt1.format(dt1));
				//outra opção
				System.out.println("dt1 = " + dt1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				
				//com hora
				System.out.println("dt2 = " + dt2.format(fmtT2));
				
				//com Instant - para imprimir uma data global de modo customizado, tenho que explicitar qual é o fuso horário
				System.out.println("dt3 = " + fmT3.format(dt3));//passou do hor de londres em que estava para a hora local do usuário. o meu.
				
				//agora usando o nome do padrão no lugar do método ofPattern
				System.out.println("dt2 = " + fmtT4.format(dt2));
				
				//agora com o nome do padrão para o instant
				System.out.println("dt3 = " + fmtT5.format(dt3));
				
				
				//CONVERTER DATA-HORA LOCAL PARA GLOBAL (TEM QUE INFORMAR O TIME ZONE)
				
				LocalDate dta = LocalDate.parse("2022-07-20");
				LocalDateTime dtb = LocalDateTime.parse("2022-07-20T01:30:26");
				Instant dtc = Instant.parse("2022-07-20T01:30:26Z");
				
				//imprimir o nome dos zoneIds, se quiser lembrar quais são
				/*
				 * for (String s : ZoneId.getAvailableZoneIds()) { System.out.println(s);
				 * 
				 * }
				 */
				//converter o meu intant para um hor. local, considerando o fuso do meu pc
				LocalDate r1 = LocalDate.ofInstant(dtc, ZoneId.systemDefault());// o fuso do meu pc
				System.out.println("r1 = " + r1);
				
				//com um fuso diferente - portugal, por ex
				LocalDate r2 = LocalDate.ofInstant(dtc, ZoneId.of("Portugal"));// o fuso de Portugal
				System.out.println("r2 = " + r2);
				
				//com hora
				LocalDateTime r3 = LocalDateTime.ofInstant(dtc, ZoneId.systemDefault());// o fuso do meu pc
				System.out.println("r3 = " + r3);
				
				LocalDateTime r4 = LocalDateTime.ofInstant(dtc, ZoneId.of("Portugal"));// o fuso de Portugal
				System.out.println("r4 = " + r4);					
				
				
				//ORDENAR DADOS DE UMA DATA-HORA LOCAL DATA-HORA LOCAL PARA DIA, MÊS, ANO, HORÁRIO
				
				//obter o dia, mês e ano isoladamente
				System.out.println("dt2 dia, mês e ano isoladamente = " + dt2.getDayOfMonth() + ", " + dt2.getMonthValue() + ", " + dt2.getYear());
				
				//obtendo a hora e minuto isoladamente
				System.out.println("dtb hora e minutos isoladamente: " + dtb.getHour() + ", " + dtb.getMinute());				
				
				
				//CÁLCULOS COM DATA-HORA DATA-HORA +/- TEMPO PARA DATA-HORA E DATA-HORA 1, DATA-HORA 2 PARA DURAÇÃO
				//instanciar uma nova data que calcula a data do dta menos 7 dias
				LocalDate pastWeekLocalDate = dta.minusDays(7);
				//acrescentar dias
				LocalDate nextWeekLocalDate = dta.plusDays(7);
				
				System.out.println("data do dta menos 7 dias: " + pastWeekLocalDate);
				System.out.println("data do dta mais 7 dias: " + nextWeekLocalDate);
				
				//com horas
				LocalDateTime minusHoursLocalDateTime = dtb.minusHours(2);
				
				LocalDateTime plusHoursLocalDateTime = dtb.plusHours(2);
				
				System.out.println("data do dtb menos 2 horas: " + minusHoursLocalDateTime);
				System.out.println("data do dtb mais 2 horas: " + plusHoursLocalDateTime);
				
				//com Instant - é diferente
				Instant pastWeekInstant = dtc.plus(7, ChronoUnit.DAYS);

				Instant nextWeekInstant = dtc.minus(7, ChronoUnit.DAYS);

				System.out.println("data do dtc instant mais 7 dias: " + pastWeekInstant);
				System.out.println("data do dtc intant menos 7 dias: " + nextWeekInstant);
				
				//duração entre duas datas
				
				Duration t1 = Duration.between(minusHoursLocalDateTime, dtb);
				System.out.println("duração entre minusHoursLocalDateTime menos dtb : " + t1);
				System.out.println("t1 para minutos = " + t1.toMinutes());
				
				

	}

}
