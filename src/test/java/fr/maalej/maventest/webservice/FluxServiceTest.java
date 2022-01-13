package fr.maalej.maventest.webservice;

import fr.maalej.maventest.exceptions.NoSuchElementFoundException;
import fr.maalej.maventest.exceptions.IllegalArgumentException;
import fr.maalej.maventest.model.Entry;
import fr.maalej.maventest.util.EntryResourceReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;

import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FluxServiceTest {

	@Mock
	private EntryResourceReader entryResourceReader;

	@InjectMocks
	private FluxService fluxService;

	private void prepareData() {
		fluxService.init();
	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.doReturn(DataTest.mockedEntries).when(entryResourceReader).read();
		this.prepareData();
	}


	@Test
	public void shoud_find_all_entries_in_memory() {
		List<Entry> entries = fluxService.findAll();
		Assert.assertEquals(7, entries.size());
		Assert.assertEquals(new Entry(1, "allianz", new Date(2021, 10, 1)), entries.get(0));
		Assert.assertEquals(new Entry(2, "toto", new Date(2021, 10, 2)), entries.get(1));
		Assert.assertEquals(new Entry(3, "oto", new Date(2021, 10, 1)), entries.get(2));
		Assert.assertEquals(new Entry(4, "123456654321", new Date(2021, 10, 3)), entries.get(3));
		Assert.assertEquals(new Entry(5, "kayak", new Date(2021, 10, 4)), entries.get(4));
		Assert.assertEquals(new Entry(6, "radar", new Date(2021, 10, 5)), entries.get(5));
		Assert.assertEquals(new Entry(7, "sagas", new Date(2021, 10, 2)), entries.get(6));
	}

	@Test
	public void shoud_find_entry_in_memory_by_id() {
		Assert.assertEquals(new Entry(2, "toto", new Date(2021, 10, 2)), fluxService.find(2));
		Assert.assertEquals(new Entry(7, "sagas", new Date(2021, 10, 2)), fluxService.find(7));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shoud_throw_exception_when_id_isNull() {
		fluxService.find(null);
	}

	@Test(expected = NoSuchElementFoundException.class)
	public void shoud_throw_exception_when_id_not_present() {
		fluxService.find(25);
	}

	@Test
	public void shoud_create_an_new_entry() {
		fluxService.create(new Entry(8, "test creation 1", new Date(2022, 1, 13)));
		fluxService.create(new Entry(9, "test creation 2", new Date(2022, 1, 13)));

		List<Entry> entries = fluxService.findAll();
		Assert.assertEquals(9, entries.size());

		this.prepareData();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shoud_not_create_an_entry_with_same_id() {
		fluxService.create(new Entry(1, "test creation 1", new Date(2022, 1, 13)));
	}

	@Test
	public void shoud_delete_an_entry() {
		fluxService.delete(1);

		List<Entry> entries = fluxService.findAll();
		Assert.assertEquals(6, entries.size());
		this.prepareData();
	}

	@Test(expected = NoSuchElementFoundException.class)
	public void shoud_throw_exception_on_deletion_when_id_not_present() {
		fluxService.delete(15);
	}

	@Test
	public void shoud_update_an_entry() {
		fluxService.update(new Entry(1, "test update 1", new Date(2022, 1, 13)));

		Assert.assertEquals(new Entry(1, "test update 1", new Date(2022, 1, 13)), fluxService.find(1));
		this.prepareData();
	}

	@Test(expected = NoSuchElementFoundException.class)
	public void shoud_throw_exception_on_update_when_id_not_present() {
		fluxService.update(new Entry(15, "test update 1", new Date(2022, 1, 13)));
	}
}
