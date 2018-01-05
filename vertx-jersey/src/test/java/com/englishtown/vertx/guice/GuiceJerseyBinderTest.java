package com.englishtown.vertx.guice;

import com.englishtown.vertx.jersey.inject.VertxPostResponseProcessor;
import com.englishtown.vertx.jersey.inject.VertxRequestProcessor;
import com.englishtown.vertx.jersey.inject.VertxResponseProcessor;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;
import jersey.repackaged.com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GuiceJerseyBinderTest {

    GuiceJerseyBinder binder;

    @Mock
    Binder builder;
    @Mock
    AnnotatedBindingBuilder annotatedBindingBuilder;

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {

        when(builder.skipSources(Matchers.<Class[]>anyVararg())).thenReturn(builder);
        when(builder.bind((Class<?>) any(Class.class))).thenReturn(annotatedBindingBuilder);
        when(annotatedBindingBuilder.to(any(Class.class))).thenReturn(mock(ScopedBindingBuilder.class));

        binder = new GuiceJerseyBinder();
    }

    @Test
    public void testConfigure() throws Exception {

        binder.configure(builder);

        // 9 types are explicitly bound
        verify(builder, times(9)).bind((Class<?>) any(Class.class));

        // 11 multi-bindings are installed
        verify(builder, times(11)).install(any(Module.class));

    }

    @Test
    public void testProviders() throws Exception {

        List<?> list;

        list = binder.provideVertxRequestProcessorList(Sets.newHashSet(mock(VertxRequestProcessor.class), mock(VertxRequestProcessor.class)));
        assertEquals(2, list.size());

        list = binder.provideVertxResponseProcessorList(Sets.newHashSet(mock(VertxResponseProcessor.class), mock(VertxResponseProcessor.class)));
        assertEquals(2, list.size());

        list = binder.provideVertxPostResponseProcessorList(Sets.newHashSet(mock(VertxPostResponseProcessor.class), mock(VertxPostResponseProcessor.class)));
        assertEquals(2, list.size());

    }

}